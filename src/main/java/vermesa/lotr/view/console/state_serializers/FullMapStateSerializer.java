package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.game.Game;

public class FullMapStateSerializer {
    public static String serialize(Game game) {
        StringBuilder builder = new StringBuilder();
        builder.append(CoinsSerializer.serialize("Coins in reserve", game.getState().getTotalCoins()));

        builder.append(LandmarkTileSerializer.serialize(game.getState().getCurrentlyUsableLandmarkTiles()));
        builder.append(CentralBoardSerializer.serialize(game.getContext().getCentralBoard()));

        builder.append(QuestOfTheRingTrackSerializer.seralize(game.getContext().getQuestOfTheRingTrack()));


        // This is correct
        builder.append(ChapterCardSetSerializer.serialize(game.getState().getCurrentRoundInformation().getChapterCards()));
        // This is testing
        //var secondRoundChapterSet = game.getContext().getRoundInformations().get(2).getChapterCards();
        //builder.append(ChapterCardSetSerializer.serialize(secondRoundChapterSet));

        return builder.toString();
    }
}
