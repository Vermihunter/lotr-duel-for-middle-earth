package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public class OneTimeImmediateRaceEffect implements IAction {
    private final IAction action;

    public OneTimeImmediateRaceEffect(IAction action) {
        this.action = action;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return action.action(ctx, state);
    }
}
