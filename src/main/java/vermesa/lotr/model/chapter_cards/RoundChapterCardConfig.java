package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

public record RoundChapterCardConfig(ArrayList<ChapterCardConfigBuilder> configs) {
    /*
    private final ArrayList<ChapterCardConfigBuilder> configs;

    public RoundChapterCardConfig(ArrayList<ChapterCardConfigBuilder> configs) {
        this.configs = configs;
    }

    public ArrayList<ChapterCardConfigBuilder> getConfigs() {
        return configs;
    }
    */
}
