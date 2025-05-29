package vermesa.lotr.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vermesa.lotr.config.ServerServiceKeys;
import vermesa.lotr.server.lobby.LobbyServiceFactory;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ResourceBundle serverConfig = ResourceBundle.getBundle("server");

        int port = Integer.parseInt(serverConfig.getString("port"));
        // String host = serverConfig.getString("host");

        Registry registry = LocateRegistry.createRegistry(port);

        registry.rebind(ServerServiceKeys.LOBBY_SERVICE_FACTORY_NAME, new LobbyServiceFactory());

        log.info("Server started on port {}", port);
    }
}
