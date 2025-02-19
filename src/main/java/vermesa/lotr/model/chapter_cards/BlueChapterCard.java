package vermesa.lotr.model.chapter_cards;

public class BlueChapterCard extends ChapterCard {
    private final int moveCount;

    public BlueChapterCard(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getMoves() {
        return moveCount;
    }
}
