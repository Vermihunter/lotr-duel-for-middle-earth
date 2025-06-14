package vermesa.lotr.model.game;

import vermesa.lotr.model.chapter_cards.ChapterCardConfigBuilder;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;

import java.util.ArrayList;

public class DefaultRoundConfigCreator {
    public static ArrayList<RoundConfig> getDefaultRoundConfigs() {
        ArrayList<RoundConfig> defaultRoundConfigs = new ArrayList<>();

        defaultRoundConfigs.add(createDefaultFirstRoundConfig());
        defaultRoundConfigs.add(createDefaultSecondRoundConfig());
        defaultRoundConfigs.add(createDefaultThirdRoundConfig());

        return defaultRoundConfigs;
    }

    private static RoundConfig createDefaultFirstRoundConfig() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        return new RoundConfig(chapterCardConfigBuilders, 1, chapterCardContexts);
    }

    private static RoundConfig createDefaultSecondRoundConfig() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        return new RoundConfig(chapterCardConfigBuilders, 1, chapterCardContexts);
    }

    private static RoundConfig createDefaultThirdRoundConfig() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        return new RoundConfig(chapterCardConfigBuilders, 1, chapterCardContexts);
    }
}
