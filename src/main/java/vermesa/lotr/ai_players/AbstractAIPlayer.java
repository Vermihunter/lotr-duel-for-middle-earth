package vermesa.lotr.ai_players;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.List;

public abstract class AbstractAIPlayer implements IAIPlayer {
    protected Player me;

    @Override
    public final List<IAction> play(Game game) {
        if (game.getState().getPlayerOnMove() != me) {
            throw new IllegalArgumentException("Its not this AI player's turn to move!");
        }

        var possibleMoves = game.getPossibleMoves();
        var chosenMoves = chooseMoves(possibleMoves);

        game.makeMove(chosenMoves);

        return chosenMoves;
    }

    protected abstract List<IAction> chooseMoves(List<List<IMove>> possibleMoves);
}
