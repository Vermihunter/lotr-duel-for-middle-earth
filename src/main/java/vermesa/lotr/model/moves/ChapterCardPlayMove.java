package vermesa.lotr.model.moves;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;

public class ChapterCardPlayMove extends ChapterCardMove {

    public ChapterCardPlayMove(ChapterCard chapterCard) {
        super(chapterCard);
    }

    @Override
    public MoveResult playMove(GameContext ctx, GameState state) {
        for (var action : chaptercard.context().actions()) {
            action.action(ctx, state);
        }

        // Some race effects have an extra effect if a player plays a specific colored chapter card
        /*
        for(var postAction : state.getPlayerOnMove().getChapterCardEventTrigger(this.chaptercard.getContext().color())) {
            postAction.action(ctx, state);
        }

         */

        // TODO: move result
        return new MoveResult();
    }
}
