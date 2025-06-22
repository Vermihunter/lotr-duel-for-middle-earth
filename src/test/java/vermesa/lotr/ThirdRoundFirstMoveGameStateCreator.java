package vermesa.lotr;

import vermesa.lotr.model.central_board.DefaultRegionBuilder;
import vermesa.lotr.model.game.*;
import vermesa.lotr.model.landmark_effects.DefaultLandmarkTileBuilder;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@TestedGameStateCreatorInfo(
        state = TestedGameState.THIRD_ROUND_FIRST_MOVE
)
public class ThirdRoundFirstMoveGameStateCreator implements TestedGameStateCreator {


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
                .withCurrentRoundNumber(3)
                .withCurrentRoundInformation(context.getRoundInformations().get(2))
                .withAllianceTokens(currentlyAvailableAllianceTokens)
                .withStartingLandmarkTiles(currentlyAvailableLandmarkTiles)
                .build();

        return new Game(context, gameState);
    }
}
