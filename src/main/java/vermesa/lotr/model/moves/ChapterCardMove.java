package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;

public abstract class ChapterCardMove implements IMove {
    public final ChapterCard chaptercard;

    public ChapterCardMove(ChapterCard chaptercard) {
        this.chaptercard = chaptercard;
    }

    @Override
    public abstract ActionResult action(GameContext ctx, GameState state);

    protected void onSuccessfulMove(GameState state) {
        state.getCurrentRoundInformation().getChapterCards().moveSuccessful(chaptercard);
    }
}
