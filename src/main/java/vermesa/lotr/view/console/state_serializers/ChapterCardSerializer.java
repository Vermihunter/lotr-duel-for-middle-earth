package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.chapter_cards.ChapterCard;
import vermesa.lotr.view.console.move_serializers.SkillSetSerializer;

public class ChapterCardSerializer {

    public static String serialize(ChapterCard chapterCard, int leadingSpaceCount) {

        var context = chapterCard.context();
        var leadingSpaces = " ".repeat(leadingSpaceCount);
        StringBuilder builder = new StringBuilder();
        // Color
        builder//.append(leadingSpaces)
                .append("→ Color: ")
                .append(context.color().toString().toLowerCase())
                .append(" - ");

        // Coins to play
        builder//.append(leadingSpaces)
                .append("Coins to play: ")
                .append(context.coinsToPlay());

        builder//.append(leadingSpaces)
                .append(" - Required skills: [")
                .append(SkillSetSerializer.serialize(context.requiredSkillSet(), leadingSpaceCount + 4))
                .append("]\n");



        // Gained chaining symbol
        if (context.gainedChainingSymbol() != null) {
            builder.append(leadingSpaces)
                    .append("Gained chaining symbol: ")
                    .append(context.gainedChainingSymbol().toString().toLowerCase());
            //        .append("\n");

            if (context.playForFreeChainingSymbol() == null) {
                builder.append("\n");
            }
        }


        // Play for free chaining symbol
        if (context.playForFreeChainingSymbol() != null) {
            if (context.gainedChainingSymbol() == null) {
                builder.append(leadingSpaces);
            }

            builder//.append(leadingSpaces)
                    .append("Play for free chaining symbol: ")
                    .append(context.playForFreeChainingSymbol().toString().toLowerCase());
//                    .append("\n");
        }

        return builder.toString();
    }
}
