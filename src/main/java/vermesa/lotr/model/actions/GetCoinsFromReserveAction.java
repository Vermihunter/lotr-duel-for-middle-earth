package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public class GetCoinsFromReserveAction implements IAction {
    private final int coinsToGet;

    public GetCoinsFromReserveAction(int coinsToGet) {
        this.coinsToGet = coinsToGet;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
