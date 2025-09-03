package vermesa.lotr.model.game;

import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;

import java.io.Serializable;

public class RoundInformation implements Serializable {
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
