package vermesa.lotr.server.lobby;

import vermesa.lotr.controllers.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.serialization.IGameConfig;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.player.RemotePlayerInformation;

public class LobbyServiceImpl extends UnicastRemoteObject implements LobbyService {
    private static final Logger log = LoggerFactory.getLogger(LobbyServiceImpl.class);

    private final LobbyRegistry lobbyRegistry;
    private final RemotePlayerInformation clientInfo;
    private final IGameConfig gameConfig;

    protected LobbyServiceImpl(LobbyRegistry lobbyRegistry, LobbyEventListener clientListener, GameEventListener gameEventListener, UUID clientId, IGameConfig gameConfig) throws RemoteException {
        this.lobbyRegistry = lobbyRegistry;
        this.clientInfo = new RemotePlayerInformation(clientId, clientListener, gameEventListener);
        this.gameConfig = gameConfig;
    }

    @Override
    public List<LobbyInfo> listLobbies() throws RemoteException {
        return lobbyRegistry.getLobbies().stream()
                .map(Lobby::extractInfo)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createLobby(String lobbyName) throws RemoteException {
        UUID uuid = UUID.randomUUID();
        leaveCurrentLobby();

        lobbyRegistry.registerLobby(new Lobby(lobbyName, clientInfo));
        log.info("Created lobby {} with ID {}", lobbyName, uuid);
        return uuid;
    }

    @Override
    public LobbyInfo joinLobby(String lobbyName) throws RemoteException {

        Lobby lobby = lobbyRegistry.getLobbyByName(lobbyName);
        // No such lobby exists
        if (lobby == null) {
            return null;
        }

        // Lobby is already full
        if (lobby.getEnemy() != null) {
            return null;
        }

        leaveCurrentLobby();
        lobby.setEnemy(clientInfo);
        return lobby.extractInfo();
    }

    @Override
    public void leaveCurrentLobby() throws RemoteException {
        LobbyFindResult clientLobby = findClientLobby();
        if (clientLobby != null) {

            switch (clientLobby.state) {
                case OWNER -> leaveOwnerLobby(clientLobby.lobby);
                case OPPONENT -> leaveNonOwnerLobby(clientLobby.lobby);
                case NONE -> throw new RemoteException("Lobby not owned");
            }
        }
    }

    private LobbyFindResult findClientLobby() {
        for (var lobby : lobbyRegistry.getLobbies()) {
            LobbyClientState state = lobby.getState(clientInfo.id());

            if (state != LobbyClientState.NONE) {
                return new LobbyFindResult(lobby, state);
            }
        }

        return null;
    }

    private void leaveNonOwnerLobby(Lobby lobby) throws RemoteException {
        lobby.setEnemy(null);
        clientInfo.lobbyEventListener().onEnemyLeave();
        log.info("Player with ID {} has left lobby {}", clientInfo.id(), lobby.getName());
    }

    private void leaveOwnerLobby(Lobby lobby) throws RemoteException {
        if (lobby.getEnemy() != null) {
            lobby.setOwner(lobby.getEnemy());
            lobby.setEnemy(null);
            clientInfo.lobbyEventListener().onEnemyLeave();
        } else {
            lobbyRegistry.deleteLobby(lobby);
        }

        log.info("Player with ID {} has left lobby {}", clientInfo.id(), lobby.getName());
    }

    @Override
    public IOpponentController startGame() throws RemoteException {
        LobbyFindResult clientLobbyResult = findClientLobby();
        if (clientLobbyResult == null) {
            log.info("Start game called when the client is not in a lobby");
            return null;
        }

        Lobby lobby = clientLobbyResult.lobby;
        if (lobby.getEnemy() == null) {
            log.info("Start game called when there is no enemy in the lobby");
            return null;
        }

        Game game = gameConfig.createGame(new Random());
        //Game game = UnicastRemoteObject.exportObject(_game, 5000);
        Player fellowshipPlayer = game.context().getFellowshipPlayer();
        Player sauronPlayer = game.context().getSauronPlayer();

        OpponentNetworkController enemyController = new OpponentNetworkController(
                new OpponentControllerContext(game, fellowshipPlayer, sauronPlayer),
                lobby.getOwner().gameEventListener());

        OpponentNetworkController ownerController = new OpponentNetworkController(
                new OpponentControllerContext(game, sauronPlayer, fellowshipPlayer),
                lobby.getEnemy().gameEventListener());

        enemyController.opponentNetworkController = ownerController;
        ownerController.opponentNetworkController = enemyController;

        IOpponentController remoteOwnerController = (IOpponentController) UnicastRemoteObject.exportObject(ownerController, 5000);
        IOpponentController remoteEnemyController = (IOpponentController) UnicastRemoteObject.exportObject(enemyController, 5000);


        RemotePlayerInformation enemy = lobby.getEnemy();
        enemy.lobbyEventListener().onGameStarted(remoteEnemyController);
        log.info("Game has been started");

        return remoteOwnerController;
    }

    private record LobbyFindResult(Lobby lobby, LobbyClientState state) {
    }
}
