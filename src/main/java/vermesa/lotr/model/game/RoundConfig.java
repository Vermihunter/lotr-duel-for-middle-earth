package vermesa.lotr.model.game;

import vermesa.lotr.model.chapter_cards.ChapterCardConfigBuilder;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;

import java.util.ArrayList;

/**
 * Represents a configuration for the current round - helper object to construct round informations
 */
public class RoundConfig {
    public final ArrayList<ChapterCardConfigBuilder> chapterCardConfigs;
    public final int coinsPerChapterCardDiscard;
    public final ArrayList<ChapterCardContext> roundChaptercardsContexts;

    public RoundConfig(ArrayList<ChapterCardConfigBuilder> chapterCardConfigs, int coinsPerChapterCardDiscard, ArrayList<ChapterCardContext> roundChaptercardsContexts) {
        this.chapterCardConfigs = chapterCardConfigs;
        this.coinsPerChapterCardDiscard = coinsPerChapterCardDiscard;
        this.roundChaptercardsContexts = roundChaptercardsContexts;
    }
}
