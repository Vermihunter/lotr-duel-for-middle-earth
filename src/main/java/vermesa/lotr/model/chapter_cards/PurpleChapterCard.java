package vermesa.lotr.model.chapter_cards;

public class PurpleChapterCard extends ChapterCard {
    private final int takeEnemyCoinCount;
    private final int moveOneUnitToAdjacentRegionCount;
    private final int removeOneEnemyUnitFromAnyRegionCount;

    public PurpleChapterCard(int takeEnemyCoinCount, int moveOneUnitToAdjacentRegionCount, int removeOneEnemyUnitFromAnyRegionCount) {
        super(null, null, false);
        this.takeEnemyCoinCount = takeEnemyCoinCount;
        this.moveOneUnitToAdjacentRegionCount = moveOneUnitToAdjacentRegionCount;
        this.removeOneEnemyUnitFromAnyRegionCount = removeOneEnemyUnitFromAnyRegionCount;
    }

    public int getRemoveOneEnemyUnitFromAnyRegionCount() {
        return removeOneEnemyUnitFromAnyRegionCount;
    }

    public int getMoveOneUnitToAdjacentRegionCount() {
        return moveOneUnitToAdjacentRegionCount;
    }

    public int getTakeEnemyCoinCount() {
        return takeEnemyCoinCount;
    }
}
