package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.central_board.Region;

public class RedChapterCard extends ChapterCard {
    private final int unitCount;
    private final Region regionFellowship;
    private final Region regionSauron;

    public RedChapterCard(int unitCount, Region regionFellowship, Region regionSauron) {
        super(null, null, false);
        this.unitCount = unitCount;
        this.regionFellowship = regionFellowship;
        this.regionSauron = regionSauron;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public Region getRegionFellowship() {
        return regionFellowship;
    }

    public Region getRegionSauron() {
        return regionSauron;
    }
}
