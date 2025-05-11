package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;
import java.util.List;

public class RoundChapterCardSet {
    private final List<ChapterCard> allChapterCards;
    private final List<ChapterCard> playableChapterCards;
    private final RoundChapterCardConfig config;

    public RoundChapterCardSet(RoundChapterCardConfig config) {
        this.config = config;
        this.allChapterCards =  this.config.getThisRoundChapterCards();
        this.playableChapterCards = new ArrayList<>();
    }

    public List<ChapterCard> getPlayableChapterCards() {
        return playableChapterCards;
    }
}
