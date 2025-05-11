package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public interface IAction {
    public ActionResult action(GameContext ctx, GameState state);
}
