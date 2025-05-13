package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a container for the chapter cards
 */
public class RoundChapterCardSet {
    private final HashMap<Integer, ChapterCardWrapper> allChapterCards;
    private final ArrayList<ChapterCard> playableChapterCards;

    private RoundChapterCardSet(HashMap<Integer, ChapterCardWrapper> allChapterCards, ArrayList<ChapterCard> playableChapterCards) {
        this.allChapterCards = allChapterCards;
        this.playableChapterCards = playableChapterCards;
    }


    public List<ChapterCard> getPlayableChapterCards() {
        return playableChapterCards;
    }

    public static RoundChapterCardSet from(List<ChapterCard> chapterCards, RoundChapterCardConfig config) {
        HashMap<Integer, ChapterCardWrapper> _allChapterCards = new HashMap<>();
        ArrayList<ChapterCard> playableChapterCards = new ArrayList<>();

        for (var chapterCardConfig : config.configs()) {
            int id = chapterCardConfig.id();
            boolean isFaceUp = chapterCardConfig.isFaceUp();
            var dependsOn = chapterCardConfig.dependsOn();

            // Get the chapter card with ID that is being configured
            var chaptercardOptional = chapterCards.stream()
                    .filter(ch -> ch.id() == id)
                    .findFirst();

            // For every configuration there should be a ChapterCard with matching ID
            if (chaptercardOptional.isEmpty()) {
                throw new IllegalArgumentException("No chapter card found for id " + id);
            }

            // Create wrapper
            ChapterCard chapterCard = chaptercardOptional.get();
            ChapterCardWrapper wrapper = new ChapterCardWrapper(chapterCard, isFaceUp, dependsOn);
            var prev = _allChapterCards.put(id, wrapper);

            // Add cards that have no dependencies to the initial list of playable cards
            if (dependsOn.isEmpty()) {
                playableChapterCards.add(chapterCard);
            }

            // IDs should be unique
            if (prev != null) {
                throw new IllegalArgumentException("Duplicate chapter card id " + id);
            }
        }

        return new RoundChapterCardSet(_allChapterCards, playableChapterCards);
    }

    /**
     * Called from Game after a chapter card move was successful to update state
     * of other chapter cards.
     *
     * @param chapterCard The chapter card that was played successfully
     */
    public void moveSuccessful(ChapterCard chapterCard) {
        int id = chapterCard.id();
        // Decrease the remaining dependency count on all chapter card that is dependent
        // on the successfully played on
        allChapterCards.values().stream()
                .filter(ch -> ch.dependsOn.contains(id))
                .forEach(this::decreaseDependencies);

        // Remove the card from playable ones
        playableChapterCards.remove(chapterCard);
    }

    private void decreaseDependencies(ChapterCardWrapper chapterCard) {
        chapterCard.remainingDependencies--;

        if (chapterCard.remainingDependencies == 0) {
            chapterCard.reveal();
            playableChapterCards.add(chapterCard.chapterCard);
        }
    }


    private static class ChapterCardWrapper {
        private final ChapterCard chapterCard;
        private final ArrayList<Integer> dependsOn;
        private boolean _isFaceUp;
        private int remainingDependencies;

        private ChapterCardWrapper(ChapterCard chapterCard, boolean isFaceUp, ArrayList<Integer> dependsOn) {
            this.chapterCard = chapterCard;
            this._isFaceUp = isFaceUp;
            this.dependsOn = dependsOn;
            this.remainingDependencies = dependsOn.size();
        }

        public void reveal() {
            this._isFaceUp = true;
        }

        public boolean isFaceUp() {
            return this._isFaceUp;
        }
    }
}
