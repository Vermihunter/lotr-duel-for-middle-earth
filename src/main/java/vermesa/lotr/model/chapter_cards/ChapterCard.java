package vermesa.lotr.model.chapter_cards;

import java.io.Serializable;

/**
 * Represents a concrete chapter card that is present in one of the rounds
 *
 * @param id      The ID of the chapter card
 * @param context The context of the chapter card containing all necessary information about it
 */
public record ChapterCard(int id, ChapterCardContext context) implements Serializable {
}
