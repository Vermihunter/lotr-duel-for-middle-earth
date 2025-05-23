package vermesa.lotr.ai_players;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.MoveResult;

import java.util.List;

/**
 * Interface that the AI players have to implement
 */
public interface IAIPlayer {
    /**
     * Makes a move for the given AI player and returns the moves made
     *
     * @param game The game context where the move to be made
     * @return The moves that were made by the player
     */
    MoveResult play(Game game);
}
