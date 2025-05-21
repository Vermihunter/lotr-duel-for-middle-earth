package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.game.Game;

public class FullMapStateSerializer {
    public static String serialize(Game game) {
        StringBuilder builder = new StringBuilder();
        builder.append(QuestOfTheRingTrackSerializer.seralize(game.getContext().getQuestOfTheRingTrack()));

        return builder.toString();
    }
}
