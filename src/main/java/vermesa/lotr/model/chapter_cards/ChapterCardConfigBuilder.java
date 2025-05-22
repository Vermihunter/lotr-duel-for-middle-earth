package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

/**
 * Helper class to build the chapter cards
 *
 * @param id        ID of the chapter card
 * @param row       The row that the chapter card is in
 * @param dependsOn The list of chapter card IDs that this one depends on
 * @param isFaceUp  Boolean value representing whether the chapter card is face up or not
 */
public record ChapterCardConfigBuilder(int id, int row, ArrayList<Integer> dependsOn, boolean isFaceUp) {
}
