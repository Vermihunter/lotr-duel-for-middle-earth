package vermesa.lotr.model.actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;

/**
 * Represents an action to be made.
 * There are two types of actions:
 * - ones that are implementations/follow-up moves (those implement only IAction)
 * - ones that implement IMove as well, these are types that the players can choose from during the game
 */
public interface IAction {
    /**
     * Executes the action to be made.
     * This function most likely makes changes in the GameState and/or in the GameContext objects
     *
     * @param ctx   Context of the game
     * @param state State of the game
     * @return Result that represents what the next actions will have to be
     */
    ActionResult action(GameContext ctx, GameState state);
}
