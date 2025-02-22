package vermesa.lotr.model.chapter_cards;

public class BlueChapterCard extends ChapterCard {
    private final int moveCount;

    public BlueChapterCard(int moveCount) {
        super(null, null, false);
        this.moveCount = moveCount;
    }

    public int getMoves() {
        return moveCount;
    }
}
