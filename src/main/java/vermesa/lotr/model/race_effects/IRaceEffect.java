package vermesa.lotr.model.race_effects;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public interface IRaceEffect extends IAction {
    public RaceEffectApplyAttributes apply();
    public boolean isOneTimeCard();

    default ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
