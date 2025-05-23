package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet.ChapterCardWrapper;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;

/**
 * Base class for the two chapter card moves:
 * <ul>
 *     <li>{@link ChapterCardDiscardMove}</li>
 *     <li>{@link ChapterCardPlayMove}</li>
 * </ul>
 */
public abstract class ChapterCardMove implements IMove {
    public final ChapterCardWrapper chapterCard;

    public ChapterCardMove(ChapterCardWrapper chaptercard) {
        this.chapterCard = chaptercard;
    }

    @Override
    public abstract ActionResult action(GameContext ctx, GameState state);

    /**
     * Common implementation on the actions that happen on a successful ChapterCardMove
     *
     * @param state The current state of the game
     */
    protected void onSuccessfulMove(GameState state) {
        state.getCurrentRoundInformation().getChapterCards().moveSuccessful(chapterCard);
    }
}
