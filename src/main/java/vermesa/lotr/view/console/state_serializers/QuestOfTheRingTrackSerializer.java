package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingTrack;
import vermesa.lotr.view.console.utils.AnsiColors;

public class QuestOfTheRingTrackSerializer {


    public static String seralize(QuestOfTheRingTrack questOfTheRingTrack) {
        int width = questOfTheRingTrack.getWidth();
        int fellowshipPlayerIndex = questOfTheRingTrack.getFellowshipPlayerIndex();
        int fellowshipPlayerMovesMade = questOfTheRingTrack.getFellowshipPlayerMovesMade();
        int sauronPlayerIndex = questOfTheRingTrack.getSauronPlayerIndex();
        int sauronPlayerMovesMade = questOfTheRingTrack.getSauronPlayerMovesMade();

        var bonusActions = questOfTheRingTrack.getBonusActions();

        int trackWidth = (width * 2) + 1;

        StringBuilder stringBuilder = new StringBuilder();

        // Title
        stringBuilder.append("╔");
        stringBuilder.append("═".repeat(trackWidth - 2));
        stringBuilder.append("╗");
        stringBuilder.append("\n║");
        stringBuilder.append(" ".repeat((trackWidth - 20) / 2));
        stringBuilder.append(AnsiColors.colorize("Quest Of Ring Track", AnsiColors.RED));
        stringBuilder.append(" ".repeat((trackWidth - 20) / 2));
        stringBuilder.append("║");
        stringBuilder.append("\n");

        stringBuilder.append("╠");
        stringBuilder.append("═╦".repeat((trackWidth - 2) / 2));
        stringBuilder.append("═");
        stringBuilder.append("╣");
        stringBuilder.append("\n");

        // Ring track
        stringBuilder.append("║");
        for (int i = 0; i < width - 1; i++) {
            // Players
            if (i == fellowshipPlayerIndex) {
                stringBuilder.append(AnsiColors.colorize("F", AnsiColors.GREEN));
                stringBuilder.append("║");
                //stringBuilder.append("F║");
            } else if (i == sauronPlayerIndex) {
                stringBuilder.append(AnsiColors.colorize("S", AnsiColors.RED));
                stringBuilder.append("║");
                //stringBuilder.append("S║");
            }
            // Bonuses
            else {
                boolean bonusFound = false;
                for (var bonusAction : bonusActions) {
                    int pos = bonusAction.pos();

                    if ((sauronPlayerMovesMade + fellowshipPlayerMovesMade + pos == i && sauronPlayerIndex < pos) // Sauron bonuses
                            || (fellowshipPlayerIndex - fellowshipPlayerMovesMade + pos == i && fellowshipPlayerMovesMade < pos) // Fellowship bonuses
                    ) {
                        stringBuilder.append(AnsiColors.colorize("B", AnsiColors.BLUE));
                        stringBuilder.append("║");
                        //stringBuilder.append("B║");
                        bonusFound = true;
                        break;
                    }
                }

                // No player, no bonus -> empty space
                if (!bonusFound) {
                    stringBuilder.append(" ║");
                }
            }
        }

        // Mount Doom
        stringBuilder.append("M║");
        stringBuilder.append("\n");

        // Lower separator
        stringBuilder.append("╚");
        stringBuilder.append("═╩".repeat((trackWidth - 2) / 2));
        stringBuilder.append("═");
        stringBuilder.append("╝");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
