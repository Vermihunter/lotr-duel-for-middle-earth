package vermesa.lotr.ai_players;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;

import java.util.List;

public interface IAIPlayer {
    /**
     * Makes a move for the given AI player and returns the moves made
     *
     * @param game The game context where the move to be made
     * @return The moves that were made by the player
     */
    List<IAction> play(Game game);
    //String getName();
}
