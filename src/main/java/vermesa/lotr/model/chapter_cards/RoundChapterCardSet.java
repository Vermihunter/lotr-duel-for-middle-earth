package vermesa.lotr.model.chapter_cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a container for the chapter cards in a concrete round
 */
public class RoundChapterCardSet implements Serializable {
    /**
     * All chapter cards present in the given round - Keys are chapter card IDs
     */
    private final HashMap<Integer, ChapterCardWrapper> allChapterCards;

    /**
     * List of chapter cards that are currently playable i.e. face up and have no dependencies
     */
    private final ArrayList<ChapterCardWrapper> playableChapterCards;

    private RoundChapterCardSet(HashMap<Integer, ChapterCardWrapper> allChapterCards, ArrayList<ChapterCardWrapper> playableChapterCards) {
        this.allChapterCards = allChapterCards;
        this.playableChapterCards = playableChapterCards;
    }

    public HashMap<Integer, ChapterCardWrapper> getAllChapterCards() {
        return allChapterCards;
    }

    /**
     * Factory method for constructing a chapter card configuration for a round
     * Constructs the chapter cards from their configuration and assigns an ID for each one
     *
     * @param chapterCards The list of all chapter cards
     * @param config       Configuration representing how each chapter card depends on others
     * @return A chapter card configuration for a concrete round
     */
    public static RoundChapterCardSet from(List<ChapterCard> chapterCards, RoundChapterCardConfig config) {
        HashMap<Integer, ChapterCardWrapper> _allChapterCards = new HashMap<>();
        ArrayList<ChapterCardWrapper> playableChapterCards = new ArrayList<>();

        for (var chapterCardConfig : config.configs()) {
            int id = chapterCardConfig.id();
            boolean isFaceUp = chapterCardConfig.isFaceUp();
            var dependsOn = chapterCardConfig.dependsOn();
            int row = chapterCardConfig.row();

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
            ChapterCardWrapper wrapper = new ChapterCardWrapper(chapterCard, isFaceUp, dependsOn, row);
            var prev = _allChapterCards.put(id, wrapper);

            // Add cards that have no dependencies to the initial list of playable cards
            if (dependsOn.isEmpty()) {
                playableChapterCards.add(wrapper);
            }

            // IDs should be unique
            if (prev != null) {
                throw new IllegalArgumentException("Duplicate chapter card id " + id);
            }
        }

        return new RoundChapterCardSet(_allChapterCards, playableChapterCards);
    }

    public List<ChapterCardWrapper> getPlayableChapterCards() {
        return playableChapterCards;
    }



    /**
     * Called from Game after a chapter card move was successful to update state
     * of other chapter cards.
     *
     * @param chapterCardWrapper The chapter card that was played successfully
     */
    public void moveSuccessful(ChapterCardWrapper chapterCardWrapper) {
        int id = chapterCardWrapper.getChapterCard().id();
        allChapterCards.get(id).alreadyPlayed = true;

        // Decrease the remaining dependency count on all chapter card that is dependent
        // on the successfully played on
        allChapterCards.values().stream()
                .filter(ch -> ch.dependsOn.contains(id))
                .forEach(this::decreaseDependencies);

        // Remove the card from playable ones
        playableChapterCards.remove(chapterCardWrapper);
    }

    /**
     * Helper function that decreases the dependencies
     * Automatically reveals if this was the last dependency
     * @param chapterCard Chapter card to decrease the number of dependencies on
     */
    private void decreaseDependencies(ChapterCardWrapper chapterCard) {
        chapterCard.remainingDependencies--;

        if (chapterCard.remainingDependencies == 0) {
            chapterCard.reveal();
            playableChapterCards.add(chapterCard);
        }
    }

    /**
     * A wrapper around chapter cards holding other necessary information about the state of the cards
     * Not a record because we didn't want to use the parenthesis syntax and some fields are used only by
     * the parent class
     */
    public static class ChapterCardWrapper implements Serializable {
        /**
         * The chapter card that is wrapped
         */
        private final ChapterCard chapterCard;

        /**
         * List of chapter card IDs that the card is dependent on
         */
        private final ArrayList<Integer> dependsOn;

        /**
         * The row which the chapter card belongs to
         */
        private final int row;

        /**
         * Boolean value representing whether the chapter card is face up i.e. publicly known
         * for players that what type of card is this
         */
        private boolean _isFaceUp;

        /**
         * The number of dependencies for this card i.e. the number of cards that has to be played
         * to make this card playable
         */
        private int remainingDependencies;

        /**
         * Boolean value representing whether the card has been already played or not
         */
        private boolean alreadyPlayed;

        private ChapterCardWrapper(ChapterCard chapterCard, boolean isFaceUp, ArrayList<Integer> dependsOn, int row) {
            this.chapterCard = chapterCard;
            this._isFaceUp = isFaceUp;
            this.dependsOn = dependsOn;
            this.remainingDependencies = dependsOn.size();
            this.row = row;
            this.alreadyPlayed = false;
        }

        public int getRemainingDependencies() {
            return remainingDependencies;
        }

        public ChapterCard getChapterCard() {
            return chapterCard;
        }

        public boolean isAlreadyPlayed() {
            return alreadyPlayed;
        }

        public int getRow() {
            return row;
        }

        public void reveal() {
            this._isFaceUp = true;
        }

        public boolean isFaceUp() {
            return this._isFaceUp;
        }
    }
}
