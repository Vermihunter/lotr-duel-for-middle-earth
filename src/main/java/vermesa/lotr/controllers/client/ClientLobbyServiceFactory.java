package vermesa.lotr.controllers.client;

import vermesa.lotr.config.ServerServiceKeys;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.server.lobby.LobbyService;
import vermesa.lotr.server.lobby.LobbyServiceFactoryRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClientLobbyServiceFactory {
    public static LobbyService lobbyService;

    public static void initialize(LobbyEventListener lobbyEventListener, GameEventListener gameEventListener) {
        ResourceBundle serverBundle = ResourceBundle.getBundle("server");
        String host = serverBundle.getString("host");
        int port = Integer.parseInt(serverBundle.getString("port"));

        String lobbyServiceFactoryUrl = "rmi://" + host + ":" + port + "/" + ServerServiceKeys.LOBBY_SERVICE_FACTORY_ENDPOINT;

        LobbyServiceFactoryRemote factory = getLobbyServiceFactory(lobbyServiceFactoryUrl);
        lobbyService = getLobbyService(factory, lobbyEventListener, gameEventListener);

        if (factory == null || lobbyService == null) {
            //   System.err.println("Unable to connect to the server");
        }
    }

    private static LobbyService getLobbyService(LobbyServiceFactoryRemote lobbyServiceFactory, LobbyEventListener lobbyEventListener, GameEventListener gameEventListener) {
        if (lobbyServiceFactory == null) {
            return null;
        }

        LobbyService lobbyService = null;
        try {
            lobbyService = lobbyServiceFactory.getLobbyService(lobbyEventListener, gameEventListener, UUID.randomUUID());
        } catch (RemoteException e) {
            // Ignore
        }

        return lobbyService;
    }

    private static LobbyServiceFactoryRemote getLobbyServiceFactory(String lobbyServiceFactoryUrl) {
        try {
            return ((LobbyServiceFactoryRemote) Naming.lookup(lobbyServiceFactoryUrl));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            return null;
        }
    }
}
