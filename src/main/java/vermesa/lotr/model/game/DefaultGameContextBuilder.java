package vermesa.lotr.model.game;

import vermesa.lotr.model.central_board.DefaultRegionBuilder;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.landmark_effects.DefaultLandmarkTileBuilder;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.player.DefaultPlayerCreator;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.quest_of_the_ring_track.DefaultQuestOfTheRingTrackCreator;
import vermesa.lotr.model.race_effects.DefaultAllianceTokenCreator;

import java.util.ArrayList;
import java.util.Random;

public class DefaultGameContextBuilder {

    public static GameContext.Builder.Config buildDefaultBuilderConfig() {
        return new GameContext.Builder.Config(true, true, true);
    }

    public static GameContext buildDefaultGameContext(Random rand, GameContext.Builder.Config config) {
        Player fellowshipPlayer = DefaultPlayerCreator.createFellowshipPlayer();
        Player sauronPlayer = DefaultPlayerCreator.createSauronPlayer();

        ArrayList<Region> regions = DefaultRegionBuilder.buildDefaultRegions();
        ArrayList<LandmarkTile> landmarkTiles = DefaultLandmarkTileBuilder.buildDefaultLandmarkTiles(regions);
        var landmarkTileContext = DefaultLandmarkTileBuilder.buildDefaultLandmarkTileContext();

        var questOfTheRingTrack = DefaultQuestOfTheRingTrackCreator.createQuestOfTheRingTrack(regions);

        var roundConfigs = DefaultRoundConfigCreator.getDefaultRoundConfigs();
        var allianceTokens = DefaultAllianceTokenCreator.createDefaultAllianceTokens(regions);


        return new GameContext.Builder()
                .withPlayers(fellowshipPlayer, sauronPlayer)
                .withQuestOfTheRingTrack(questOfTheRingTrack)
                .withRoundConfigs(roundConfigs)
                .withAllianceTokens(allianceTokens)
                .withLandmarkTileContext(landmarkTileContext)
                .addRegions(regions)
                .addLandmarkTiles(landmarkTiles)
                .build(rand, config);

    }
}
