package vermesa.lotr.model.game;

import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DefaultGameStateBuilder {

    public static GameState buildDefaultGameState(Random rand, GameContext defaultGameContext) {

        var currentlyAvailableAllianceTokens = (HashMap<Race, ArrayList<AllianceToken>>) defaultGameContext.getAllianceTokens().clone();
        var currentlyAvailableLandmarkTiles = (ArrayList<LandmarkTile>) defaultGameContext.getLandmarkTiles().clone();

        return GameState.GameStateBuilder.aGameState()
                .withGameContext(defaultGameContext)
                .withPlayerOnMove(defaultGameContext.getFellowshipPlayer())
                .withNextPlayerOnMove(defaultGameContext.getSauronPlayer())
                .withCurrentRoundNumber(1)
                .withTotalCoins(30)
                .withFollowUpMoves(null)
                .withCurrentGameState(CurrentGameState.HAS_NOT_ENDED)
                .withCurrentRoundInformation(defaultGameContext.getRoundInformations().getFirst())
                .withAllianceTokens(currentlyAvailableAllianceTokens)
                .withStartingLandmarkTiles(currentlyAvailableLandmarkTiles)
                .build();

    }
}
