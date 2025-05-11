package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

public class ChapterCardConfig {
    private final int ID;
    private final ArrayList<Integer> dependsOn;
    private final boolean isFaceUp;

    public ChapterCardConfig(int id, ArrayList<Integer> dependsOn, boolean isFaceUp) {
        ID = id;
        this.dependsOn = dependsOn;
        this.isFaceUp = isFaceUp;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Integer> getDependsOn() {
        return dependsOn;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }
}
