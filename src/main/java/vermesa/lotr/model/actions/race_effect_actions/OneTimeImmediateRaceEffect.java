package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.io.Serializable;

public record OneTimeImmediateRaceEffect(IAction action) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return action.action(ctx, state);
    }
}
