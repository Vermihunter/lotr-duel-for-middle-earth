package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IEnemyMoveMadeListener;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.view.console.Context;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NetworkEnemyMoveMadeListener extends UnicastRemoteObject implements GameEventListener {
    private final IEnemyMoveMadeListener bridge;
    private final Context context;


    public NetworkEnemyMoveMadeListener(IEnemyMoveMadeListener bridge, Context context) throws RemoteException {
        this.bridge = bridge;
        this.context = context;
    }

    @Override
    public void onEnemyMoveMade(List<IAction> moves) throws RemoteException {
        // Make opponent's moves visible in our game
        context.controller.getGame().makeMove(moves);
        bridge.listen(moves, false);
    }
}
