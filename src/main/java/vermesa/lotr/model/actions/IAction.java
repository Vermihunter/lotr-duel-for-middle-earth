package vermesa.lotr.model.actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;

public interface IAction {
    ActionResult action(GameContext ctx, GameState state);
}
