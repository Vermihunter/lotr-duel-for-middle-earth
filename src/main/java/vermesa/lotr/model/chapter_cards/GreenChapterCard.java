package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.Race;

public class GreenChapterCard extends ChapterCard {
    private final Race race;

    public GreenChapterCard(Race race) {
        this.race = race;
    }

    public Race getRace() {
        return race;
    }
}
