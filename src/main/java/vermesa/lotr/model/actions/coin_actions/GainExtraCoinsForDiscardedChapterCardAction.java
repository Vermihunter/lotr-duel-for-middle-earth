package vermesa.lotr.model.actions.coin_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

/**
 * Humans race effect → takes double amount of coins for discarding Chapter cards
 */
public record GainExtraCoinsForDiscardedChapterCardAction(int[] extraCoinsPerRound) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        int currRoundNumber = state.getCurrentRoundNumber();
        Player playerOnMove = state.getPlayerOnMove();

        playerOnMove.addCoins(extraCoinsPerRound[currRoundNumber]);
        state.takeCoinsFromReserve(extraCoinsPerRound[currRoundNumber]);

        return null;
    }
}

