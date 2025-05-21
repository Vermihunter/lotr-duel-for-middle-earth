package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingTrack;

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
        stringBuilder.append("-".repeat(trackWidth));
        stringBuilder.append("\n|");
        stringBuilder.append(" ".repeat((trackWidth - 20) / 2));
        stringBuilder.append("Quest Of Ring Track");
        stringBuilder.append(" ".repeat((trackWidth - 20) / 2));
        stringBuilder.append("|");
        stringBuilder.append("\n");

        stringBuilder.append("-".repeat(trackWidth));
        stringBuilder.append("\n");

        stringBuilder.append("|");

        for (int i = 0; i < width - 1; i++) {
            if (i == fellowshipPlayerIndex) {
                stringBuilder.append("F|");
            } else if (i == sauronPlayerIndex) {
                stringBuilder.append("S|");
            } else {

                boolean bonusFound = false;
                for (var bonusAction : bonusActions) {
                    int pos = bonusAction.pos();

                    if ((sauronPlayerMovesMade + fellowshipPlayerMovesMade + pos == i && sauronPlayerIndex < pos) // Sauron bonuses
                            || (fellowshipPlayerIndex - fellowshipPlayerMovesMade + pos == i && fellowshipPlayerMovesMade < pos) // Fellowship bonuses
                    ) {
                        stringBuilder.append("B|");
                        bonusFound = true;
                        break;
                    }
                }

                if (!bonusFound) {
                    stringBuilder.append(" |");
                }

                /*
                int finalI = i;
                var res = bonusActions.stream()
                        .filter(a -> a.pos() == finalI || a.pos() + width / 2 == finalI);
                if (res.findAny().isPresent()) {
                    stringBuilder.append("B|");
                } else {
                    stringBuilder.append(" |");
                }
                */
            }
        }

        stringBuilder.append("M|");

        // stringBuilder.append(" |".repeat(trackWidth));
        stringBuilder.append("\n");
        //stringBuilder.append("|");

        stringBuilder.append("-".repeat(trackWidth));
        return stringBuilder.toString();
    }
}
