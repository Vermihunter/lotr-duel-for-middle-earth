package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

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
