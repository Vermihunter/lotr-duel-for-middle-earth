package vermesa.lotr.model.actions.basic_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;

import java.util.List;

/**
 * Represents a wrapper around a list of moves where all the moves has to be takne
 *
 * @param actions The actions to be taken
 */
public record TakeAllActions(List<? extends IAction> actions) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return IMove.performMultiStageMove(ctx, state, actions);
    }
}
