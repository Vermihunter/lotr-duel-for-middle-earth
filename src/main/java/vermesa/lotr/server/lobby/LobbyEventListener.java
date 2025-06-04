package vermesa.lotr.server.lobby;

import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.controllers.OpponentNetworkController;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LobbyEventListener extends Remote {
    void onEnemyLeave() throws RemoteException;

    void onGameStarted(IOpponentController controller) throws RemoteException;
}
