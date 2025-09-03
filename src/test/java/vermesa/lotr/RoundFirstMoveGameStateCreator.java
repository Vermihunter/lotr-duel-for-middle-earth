package vermesa.lotr;

import vermesa.lotr.model.game.*;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RoundFirstMoveGameStateCreator implements TestedGameStateCreator {
    private final int roundNumber;

    public RoundFirstMoveGameStateCreator(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public Game createGame() {
        var gameContextBuilderConfig = new GameContext.Builder.Config(false, false, false);
        var rand = new Random(1);

        var context = DefaultGameContextBuilder.buildDefaultGameContext(rand, gameContextBuilderConfig);

        var currentlyAvailableAllianceTokens = (HashMap<Race, ArrayList<AllianceToken>>) context.getAllianceTokens().clone();
        var currentlyAvailableLandmarkTiles = (ArrayList<LandmarkTile>) context.getLandmarkTiles().clone();
        var gameState = GameState.GameStateBuilder.aGameState()
                .withGameContext(context)
                .withPlayerOnMove(context.getFellowshipPlayer())
                .withNextPlayerOnMove(context.getSauronPlayer())
                .withTotalCoins(30)
                .withFollowUpMoves(null)
                .withCurrentGameState(CurrentGameState.HAS_NOT_ENDED)
                .withCurrentRoundNumber(roundNumber)
                .withCurrentRoundInformation(context.getRoundInformations().get(roundNumber - 1))
                .withAllianceTokens(currentlyAvailableAllianceTokens)
                .withStartingLandmarkTiles(currentlyAvailableLandmarkTiles)
                .build();

        return new Game(context, gameState);
    }
}
