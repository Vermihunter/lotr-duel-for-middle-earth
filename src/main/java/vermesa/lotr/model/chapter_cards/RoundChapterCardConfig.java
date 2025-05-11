package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

public class RoundChapterCardConfig {
    private final ArrayList<ChapterCardConfig> configs;

    public RoundChapterCardConfig(ArrayList<ChapterCardConfig> configs) {
        this.configs = configs;
    }

    public ArrayList<ChapterCard> getThisRoundChapterCards() {
        return null;
    }
}
