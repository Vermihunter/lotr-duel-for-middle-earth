package vermesa.lotr.server.lobby;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LobbyServiceFactory extends UnicastRemoteObject implements LobbyServiceFactoryRemote {
    private static final Logger log = LoggerFactory.getLogger(LobbyServiceFactory.class.getName());

    public LobbyServiceFactory() throws RemoteException {
    }

    @Override
    public LobbyService getLobbyService(LobbyEventListener clientListener, UUID clientID) throws RemoteException {
        // TODO: check if clientID is correct + the secret matches


        log.info("Remote factory object created for client {}", clientID);
        return new LobbyServiceImpl(LobbyRegistry.getInstance(), clientListener, clientID);
    }
}
