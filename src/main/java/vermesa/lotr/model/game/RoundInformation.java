package vermesa.lotr.model.game;

import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;

public class RoundInformation {
    private final RoundChapterCardSet chapterCards;
    private final int coinsPerDiscardedCards;

    public RoundInformation(RoundChapterCardSet chapterCards, int coinsPerDiscardedCards) {
        this.chapterCards = chapterCards;
        this.coinsPerDiscardedCards = coinsPerDiscardedCards;
    }

    public RoundChapterCardSet getChapterCards() {
        return chapterCards;
    }

    public int getCoinsPerDiscardedCards() {
        return coinsPerDiscardedCards;
    }
}
