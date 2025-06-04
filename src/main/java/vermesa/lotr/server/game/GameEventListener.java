package vermesa.lotr.server.game;

import vermesa.lotr.model.actions.IAction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GameEventListener extends Remote {
    void onEnemyMoveMade(List<IAction> moves) throws RemoteException;
}
