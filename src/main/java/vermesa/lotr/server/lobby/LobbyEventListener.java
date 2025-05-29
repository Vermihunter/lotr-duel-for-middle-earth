package vermesa.lotr.server.lobby;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LobbyEventListener extends Remote {
    void onEnemyLeave() throws RemoteException;
}
