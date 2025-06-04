package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.CurrentGameState;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param success Whether the move was successful or not
 * @param currentGameState Represents the current game state
 */
public record MoveResult(boolean success, CurrentGameState currentGameState,
                         List<IAction> movesTaken) implements Serializable {
}