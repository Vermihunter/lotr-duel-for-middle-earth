package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

public record ChapterCardConfigBuilder(int id, ArrayList<Integer> dependsOn, boolean isFaceUp) {

}
