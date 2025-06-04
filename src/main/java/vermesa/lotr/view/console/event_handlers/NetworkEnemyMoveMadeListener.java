package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IEnemyMoveMadeListener;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.server.game.GameEventListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NetworkEnemyMoveMadeListener extends UnicastRemoteObject implements GameEventListener {
    private final IEnemyMoveMadeListener bridge;

    public NetworkEnemyMoveMadeListener(IEnemyMoveMadeListener bridge) throws RemoteException {
        this.bridge = bridge;
    }

    @Override
    public void onEnemyMoveMade(List<IAction> moves) throws RemoteException {
        bridge.listen(moves, false);
    }
}
