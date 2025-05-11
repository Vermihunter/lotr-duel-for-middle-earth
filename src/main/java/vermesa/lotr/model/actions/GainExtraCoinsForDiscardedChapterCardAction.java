package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

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
