package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public class TakeEnemyCoinsAction implements IAction{
    public final int coinsToTake;

    public TakeEnemyCoinsAction(int coinsToTake) {
        this.coinsToTake = coinsToTake;

    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
