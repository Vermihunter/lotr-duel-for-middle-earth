package vermesa.lotr.model.actions.coin_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

/**
 * Humans race effect → takes double amount of coins for discarding Chapter cards
 * TODO: this is part of the event callback handlers, add it there, the configuration reader could be used
 */
public class GainExtraCoinsForDiscardedChapterCardAction implements IAction {
    private final int[] extraCoinsPerRound;

    public GainExtraCoinsForDiscardedChapterCardAction(int[] extraCoinsPerRound) {
        this.extraCoinsPerRound = extraCoinsPerRound;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}

