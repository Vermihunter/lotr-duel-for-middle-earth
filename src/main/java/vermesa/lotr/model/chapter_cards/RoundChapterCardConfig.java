package vermesa.lotr.model.chapter_cards;

import java.util.ArrayList;

/**
 * Helper class to initialize chapter cards
 *
 * @param configs The list of configurations for the chapter cards
 */
public record RoundChapterCardConfig(ArrayList<ChapterCardConfigBuilder> configs) {
}
