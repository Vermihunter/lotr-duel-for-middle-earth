package vermesa.lotr.model.actions.basic_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.io.Serializable;

/**
 *
 */
public class TakeAnotherTurnAction implements IAction, Serializable {
    private static final ActionResult CONSTANT_RESULT = new ActionResult(null, false);

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return CONSTANT_RESULT;
    }
}
