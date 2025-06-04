package vermesa.lotr.controllers;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;

import java.rmi.RemoteException;
import java.util.List;

public class OpponentAIController extends OpponentController {
    private final IAIPlayer aiPlayer;
    private final Object aiWorkerLock;

    public OpponentAIController(IAIPlayer aiPlayer, OpponentControllerContext context) {
        super(context);
        this.aiPlayer = aiPlayer;
        this.aiWorkerLock = new Object();
    }

    public Object getAiWorkerLock() {
        return aiWorkerLock;
    }

    @Override
    public MoveResult makeMove(List<IAction> move) throws RemoteException {
        var game = context.game();
        var moveResult = game.makeMove(move);

        if (game.state().getPlayerOnMove().equals(aiPlayer.getPlayer())) {
            synchronized (aiWorkerLock) {
                aiWorkerLock.notify();
            }
        }

        return moveResult;
    }

    @Override
    public List<List<IMove>> getPossibleMoves() {
        return context.game().getPossibleMoves();
    }


}
