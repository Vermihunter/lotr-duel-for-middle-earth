package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingTrack;

public class QuestOfTheRingTrackSerializer {

    public static String seralize(QuestOfTheRingTrack questOfTheRingTrack) {
        int width = questOfTheRingTrack.getWidth();
        int fellowShipPlayerIndex = questOfTheRingTrack.getFellowshipPlayerIndex();
        int sauronPlayerIndex = questOfTheRingTrack.getSauronPlayerIndex();
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

        for (int i = 0; i < width; i++) {
            if (i == fellowShipPlayerIndex) {
                stringBuilder.append("F|");
            } else if (i == sauronPlayerIndex) {
                stringBuilder.append("S|");
            } else {
                int finalI = i;
                var res = bonusActions.stream()
                        .filter(a -> a.pos() == finalI || a.pos() + width / 2 == finalI);

                if (res.findAny().isPresent()) {
                    stringBuilder.append("B|");
                } else {
                    stringBuilder.append(" |");
                }

            }
        }

        // stringBuilder.append(" |".repeat(trackWidth));
        stringBuilder.append("\n");
        //stringBuilder.append("|");

        stringBuilder.append("-".repeat(trackWidth));
        return stringBuilder.toString();
    }
}
