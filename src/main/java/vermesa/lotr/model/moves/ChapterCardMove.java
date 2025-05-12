package vermesa.lotr.model.moves;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;

public abstract class ChapterCardMove implements IMove {
    protected final ChapterCard chaptercard;

    public ChapterCardMove(ChapterCard chaptercard) {
        this.chaptercard = chaptercard;
    }

    @Override
    public abstract MoveResult playMove(GameContext ctx, GameState state);
}
