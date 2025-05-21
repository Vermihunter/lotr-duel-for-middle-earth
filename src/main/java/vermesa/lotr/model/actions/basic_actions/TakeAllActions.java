package vermesa.lotr.model.actions.basic_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;

import java.util.List;

public record TakeAllActions(List<? extends IAction> actions) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        ActionResult result = IMove.performMultiStageMove(ctx, state, actions);


        return result;
    }
}
