package vermesa.lotr.model.actions.coin_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.io.Serializable;

public record GetCoinsFromReserveAction(int coinsToGet) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        if (state.getTotalCoins() < coinsToGet) {
            throw new IllegalArgumentException("There are not enough coins in the reserve to perform this action");
        }

        state.getPlayerOnMove().addCoins(coinsToGet);
        state.takeCoinsFromReserve(coinsToGet);

        return ActionResult.OK;
    }
}
