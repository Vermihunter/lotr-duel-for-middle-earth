package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public class PlayDiscardedChapterCardAction implements IAction {
    private final int cardsToPlay;

    public PlayDiscardedChapterCardAction(int cardsToPlay) {
        this.cardsToPlay = cardsToPlay;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
