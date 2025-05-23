package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.chapter_cards.RoundChapterCardSet.ChapterCardWrapper;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;

import java.util.stream.Collectors;

public class ChapterCardSerializer {

    public static String serialize(ChapterCardWrapper chapterCard, int leadingSpaceCount) {

        var context = chapterCard.getChapterCard().context();
        var leadingSpaces = " ".repeat(leadingSpaceCount);
        StringBuilder builder = new StringBuilder();

        // ID
        builder.append("→ ID: ")
                .append(chapterCard.getChapterCard().id() + 1);

        // Color
        builder.append(" - Color: ")
                .append(context.color().toString().toLowerCase());


        // Coins to play
        builder.append(" - Coins to play: ")
                .append(context.coinsToPlay());

        builder.append(" - Required skills: [")
                .append(SkillSetSerializer.serialize(context.requiredSkillSet(), leadingSpaceCount + 4))
                .append("]\n");



        // Gained chaining symbol
        if (context.gainedChainingSymbol() != null) {
            builder.append(leadingSpaces)
                    .append("Gained chaining symbol: ")
                    .append(context.gainedChainingSymbol().toString().toLowerCase());

            if (context.playForFreeChainingSymbol() == null) {
                builder.append("\n");
            }
        }


        // Play for free chaining symbol
        if (context.playForFreeChainingSymbol() != null) {
            if (context.gainedChainingSymbol() == null) {
                builder.append(leadingSpaces);
            }

            builder.append("Play for free chaining symbol: ")
                    .append(context.playForFreeChainingSymbol().toString().toLowerCase());
        }

        var serializers = ActionSerializerRegistry.getAll();
        var actionsSerialized = chapterCard.getChapterCard().context().actions().stream()
                .map(a -> serializers.get(a.getClass()).serialize(a))
                .collect(Collectors.joining("           - "));

        builder.append("           Actions:\n           - ");
        builder.append(actionsSerialized);

        return builder.toString();
    }
}
