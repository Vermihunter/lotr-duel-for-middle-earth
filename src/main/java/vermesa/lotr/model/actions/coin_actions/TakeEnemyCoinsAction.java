package vermesa.lotr.model.actions.coin_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

public class TakeEnemyCoinsAction implements IAction {
    public final int coinsToTake;

    public TakeEnemyCoinsAction(int coinsToTake) {
        this.coinsToTake = coinsToTake;

    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player otherPlayer = state.getNextPlayerOnMove();
        otherPlayer.removeCoins(coinsToTake);
        state.putBackCoinsToReserve(coinsToTake);

        return ActionResult.OK;
    }
}
