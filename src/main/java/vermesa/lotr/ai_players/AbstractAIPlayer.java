package vermesa.lotr.ai_players;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.player.Player;

import java.util.List;

/**
 * Abstract AI player holding the common implementations of all players
 */
public abstract class AbstractAIPlayer implements IAIPlayer {
    protected final Player me;

    protected AbstractAIPlayer(Player me) {
        this.me = me;
    }

    /**
     * Implements the templater design pattern
     *
     * @param game The game context where the move to be made
     * @return The list of moves that the AI player chose
     */
    @Override
    public final List<IAction> chooseMoves(Game game) {
        if (!game.state().getPlayerOnMove().equals(me)) {
            throw new IllegalArgumentException("Its not this AI player's turn to move!");
        }

        var possibleMoves = game.getPossibleMoves();
        return chooseMoves(possibleMoves);
    }

    @Override
    public final Player getPlayer() {
        return me;
    }

    /**
     * Actual logic of the AI player
     * @param possibleMoves The possible moves that the AI player can make
     * @return List of chosen action
     */
    protected abstract List<IAction> chooseMoves(List<List<IMove>> possibleMoves);
}
