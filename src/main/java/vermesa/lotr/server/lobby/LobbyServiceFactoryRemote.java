package vermesa.lotr.server.lobby;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface LobbyServiceFactoryRemote extends Remote {
    LobbyService getLobbyService(LobbyEventListener clientListener, UUID clientID) throws RemoteException;
}
