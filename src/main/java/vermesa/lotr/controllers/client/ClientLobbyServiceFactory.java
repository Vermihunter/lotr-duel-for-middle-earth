package vermesa.lotr.controllers.client;

import vermesa.lotr.config.ServerServiceKeys;
import vermesa.lotr.server.lobby.LobbyService;
import vermesa.lotr.server.lobby.LobbyServiceFactoryRemote;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClientLobbyServiceFactory {
    public final static LobbyService lobbyService;
    private final static LobbyServiceFactoryRemote factory;

    static {
        ResourceBundle serverBundle = ResourceBundle.getBundle("server");
        String host = serverBundle.getString("host");
        int port = Integer.parseInt(serverBundle.getString("port"));

        String lobbyServiceFactoryUrl = "rmi://" + host + ":" + port + "/" + ServerServiceKeys.LOBBY_SERVICE_FACTORY_ENDPOINT;
        try {
            factory = ((LobbyServiceFactoryRemote) Naming.lookup(lobbyServiceFactoryUrl));
            lobbyService = factory.getLobbyService(new LobbyClientEventListener(), UUID.randomUUID());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
