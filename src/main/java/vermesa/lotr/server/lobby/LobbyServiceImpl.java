package vermesa.lotr.server.lobby;

import vermesa.lotr.controllers.LotrController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LobbyServiceImpl extends UnicastRemoteObject implements LobbyService {
    private static final Logger log = LoggerFactory.getLogger(LobbyServiceImpl.class);

    private final LobbyRegistry lobbyRegistry;
    private final LobbyEventListener clientListener;
    private final UUID clientId;


    protected LobbyServiceImpl(LobbyRegistry lobbyRegistry, LobbyEventListener clientListener, UUID clientId) throws RemoteException {
        this.lobbyRegistry = lobbyRegistry;
        this.clientListener = clientListener;
        this.clientId = clientId;
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

        lobbyRegistry.registerLobby(new Lobby(uuid, lobbyName, clientId));
        log.info("Created lobby {} with ID {}", lobbyName, uuid);
        return uuid;
    }

    @Override
    public LobbyInfo joinLobby(UUID lobbyUUID) throws RemoteException {

        Lobby lobby = lobbyRegistry.getLobbyById(lobbyUUID);
        // No such lobby exists
        if (lobby == null) {
            return null;
        }

        // Lobby is already full
        if (lobby.getEnemyId() != null) {
            return null;
        }

        leaveCurrentLobby();
        lobby.setEnemyId(clientId);
        return lobby.extractInfo();
    }

    @Override
    public void leaveCurrentLobby() throws RemoteException {
        for (var lobby : lobbyRegistry.getLobbies()) {
            if (lobby.getOwnerId().equals(clientId)) {
                if (lobby.getEnemyId() != null) {
                    lobby.setOwnerId(lobby.getEnemyId());
                    lobby.setEnemyId(null);
                    clientListener.onEnemyLeave();
                } else {
                    lobbyRegistry.deleteLobby(lobby);
                }

                log.info("Player with ID {} has left lobby {}", clientId, lobby.getId());
                break;
            } else if (lobby.getEnemyId() != null && lobby.getEnemyId().equals(clientId)) {
                lobby.setEnemyId(null);
                clientListener.onEnemyLeave();
                log.info("Player with ID {} has left lobby {}", clientId, lobby.getId());
                break;
            }
        }

    }


    @Override
    public LotrController startGame() throws RemoteException {
        return null;
    }
}
