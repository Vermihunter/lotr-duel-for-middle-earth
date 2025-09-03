package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public class OpponentNetworkController extends OpponentController implements Remote, Serializable {
    private final GameEventListener opponentEventListener;
    public OpponentNetworkController opponentNetworkController;

    public OpponentNetworkController(OpponentControllerContext context, GameEventListener opponentEventListener) throws RemoteException {
        super(context);
        this.opponentEventListener = opponentEventListener;
    }

    @Override
    public MoveResult makeMove(List<IAction> move) throws RemoteException {
        var moveResult = context.game().makeMove(move);

        opponentEventListener.onEnemyMoveMade(move);

        return moveResult;
    }

    @Override
    public List<List<IMove>> getPossibleMoves() {
        return context.game().getPossibleMoves();
    }

}
