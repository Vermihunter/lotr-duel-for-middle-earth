package vermesa.lotr.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;
import vermesa.lotr.serialization.utils.JsonConfigs;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    Game game;
    GameState state;

    @BeforeEach
    void setUp() {
        game = DefaultGameBuilder.buildDefaultGame(new Random(1));
        state = game.state();
    }

    @ParameterizedTest(name = "[index] Coins put back: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void putBackCoinsToReserve(int coinsToPutBack) {
        // Arrange
        int initialCoins = state.getTotalCoins();

        // Act
        state.putBackCoinsToReserve(coinsToPutBack);

        // Assert
        assertEquals(initialCoins + coinsToPutBack, state.getTotalCoins());
    }

    @ParameterizedTest(name = "[index] Coins taken from reserve: {0}")
    @ValueSource(ints = {0, 1, 2, 7, 10, 15, 21, 22, 27, 30})
    void takeCoinsFromReserve_EnoughCoins(int coinsToTake) {
        // Arrange
        int initialCoins = state.getTotalCoins();

        // Act
        assertDoesNotThrow(() -> state.takeCoinsFromReserve(coinsToTake));

        // Assert
        assertEquals(initialCoins - coinsToTake, state.getTotalCoins());
    }

    @ParameterizedTest(name = "[index] Coins taken from reserve: {0}")
    @ValueSource(ints = {31, 40, 45})
    void takeCoinsFromReserve_TooManyCoins(int coinsToTake) {
        // Arrange
        int initialCoins = state.getTotalCoins();

        // Act
        assertThrows(IllegalArgumentException.class, () -> state.takeCoinsFromReserve(coinsToTake));

        // Assert → all coins stayed in the reserve
        assertEquals(initialCoins, state.getTotalCoins());
    }

    @Test
    void shiftPlayers() {
        Player playerOnMove = state.getPlayerOnMove();
        Player nextPlayerOnMove = state.getNextPlayerOnMove();

        // Act
        state.shiftPlayers();

        // Assert
        assertEquals(playerOnMove.getType(), state.getNextPlayerOnMove().getType());
        assertEquals(nextPlayerOnMove.getType(), state.getPlayerOnMove().getType());
    }

    @Test
    void checkGameState() {

    }

    @Test
    void startNewRound() {
    }

    private void initRegions(List<Region> regions, ConqueringMiddleEarthStateConfig config) {
        // Init regions from config
        for (var c : config.Config) {
            regions.stream()
                    .filter(r -> r.getRegionType() == c.RegionType)
                    .forEach(r -> {
                        if (c.FortressHolder != null) {
                            r.placeFortress(c.FortressHolder);
                        }

                        if (c.UnitHolder != null) {
                            r.addUnits(c.UnitHolder, c.UnitCount);
                        }
                    });
        }
    }

    // For each region  → Fortress / Units
    @ParameterizedTest(name = "[index] - {0}")
    @JsonConfigs(value = ConqueringMiddleEarthStateConfig.class, resource = "/test-data/GameState-tests/conquering-me-state-test.json")
    void checkConqueringMiddleEarthState(ConqueringMiddleEarthStateConfig config) {
        // Arrange
        initRegions(game.context().getCentralBoard().regions(), config);

        // Act
        var result = state.checkConqueringMiddleEarthState();

        // Assert
        assertEquals(config.ExpectedGameState, result);
    }

    /**
     * {@link GameState#checkQuestOfTheRingState()} TESTS
     */


    private void checkQuestOfTheRingState_OnePlayerMoves(PlayerType movingPlayer, int moveCount, CurrentGameState expectedResult) {
        // Arrange → move the Sauron player on the quest of the ring track
        var ctx = game.context();
        var questOfTheRingTrack = ctx.getQuestOfTheRingTrack();
        questOfTheRingTrack.movePlayer(movingPlayer, moveCount, ctx, state);

        // Act
        var result = state.checkQuestOfTheRingState();

        // Assert
        assertEquals(expectedResult, result);
    }

    private void checkQuestOfTheRingState_BothMove(PlayerType player1, int moveCount1, PlayerType player2, int moveCount2, CurrentGameState expectedResult) {
        // Arrange → move the Sauron player on the quest of the ring track
        var ctx = game.context();
        var questOfTheRingTrack = ctx.getQuestOfTheRingTrack();
        questOfTheRingTrack.movePlayer(player1, moveCount1, ctx, state);
        questOfTheRingTrack.movePlayer(player2, moveCount2, ctx, state);

        // Act
        var result = state.checkQuestOfTheRingState();

        // Assert
        assertEquals(expectedResult, result);
    }


    /**
     * {@link GameState#checkSupportOfTheRacesState()} TESTS
     */

    /*
    @Test
    void checkSupportOfTheRacesState() {

    }
     */

    @ParameterizedTest(name = "[index] - Sauron player moved: {0}")
    @ValueSource(ints = {14, 15, 22, 27, 30})
    void checkQuestOfTheRingState_SauronWon(int sauronPlayerMoves) {
        checkQuestOfTheRingState_OnePlayerMoves(PlayerType.Sauron, sauronPlayerMoves, CurrentGameState.SAURON_WON);
    }

    @ParameterizedTest(name = "[index] - Sauron player moved: {0}")
    @ValueSource(ints = {0, 1, 2, 7, 10, 13})
    void checkQuestOfTheRingState_NoWinnerOnlySauronMove(int sauronPlayerMoves) {
        checkQuestOfTheRingState_OnePlayerMoves(PlayerType.Sauron, sauronPlayerMoves, CurrentGameState.HAS_NOT_ENDED);
    }

    @ParameterizedTest(name = "[index] - Fellowship player moved: {0}")
    @ValueSource(ints = {14, 15, 22, 27, 30})
    void checkQuestOfTheRingState_FellowshipWon(int fellowshipPlayerMoves) {
        checkQuestOfTheRingState_OnePlayerMoves(PlayerType.Fellowship, fellowshipPlayerMoves, CurrentGameState.FELLOWSHIP_WON);
    }

    @ParameterizedTest(name = "[index] - Fellowship player moved: {0}")
    @ValueSource(ints = {0, 1, 2, 7, 10, 13})
    void checkQuestOfTheRingState_NoWinnerOnlyFellowshipMove(int fellowshipPlayerMoves) {
        checkQuestOfTheRingState_OnePlayerMoves(PlayerType.Fellowship, fellowshipPlayerMoves, CurrentGameState.HAS_NOT_ENDED);
    }

    @ParameterizedTest(name = "[index] - Sauron player moved: {0} - Fellowship player moved: {1}")
    @CsvSource({
            "1,5",
            "13,13",
            "8,5",
            "5,5",
            "0,0",
            "1,1",
    })
    void checkQuestOfTheRingState_NoWinnerBothMove(int sauronPlayerMoves, int fellowshipPlayerMoves) {
        checkQuestOfTheRingState_BothMove(PlayerType.Sauron, sauronPlayerMoves, PlayerType.Fellowship, fellowshipPlayerMoves, CurrentGameState.HAS_NOT_ENDED);
    }

    @ParameterizedTest(name = "[index] - Sauron player moved: {0} - Fellowship player moved: {1}")
    @CsvSource({
            "14,5",
            "14,7",
            "14,8",
            "14,13",
            "15,0",
            "15,8",
    })
    void checkQuestOfTheRingState_SauronWinBothMove(int sauronPlayerMoves, int fellowshipPlayerMoves) {
        checkQuestOfTheRingState_BothMove(PlayerType.Sauron, sauronPlayerMoves, PlayerType.Fellowship, fellowshipPlayerMoves, CurrentGameState.SAURON_WON);
    }

    @ParameterizedTest(name = "[index] - Sauron player moved: {1} - Fellowship player moved: {0}")
    @CsvSource({
            "14,5",
            "14,7",
            "14,8",
            "14,13",
            "15,0",
            "15,8",
    })
    void checkQuestOfTheRingState_FellowshipWinBothMove(int fellowshipPlayerMoves, int sauronPlayerMoves) {
        checkQuestOfTheRingState_BothMove(PlayerType.Sauron, sauronPlayerMoves, PlayerType.Fellowship, fellowshipPlayerMoves, CurrentGameState.FELLOWSHIP_WON);
    }

    /**
     * {@link GameState#checkConqueringMiddleEarthState()} TESTS
     */

    public static class RegionInfo {
        public RegionType RegionType;
        public PlayerType FortressHolder;
        public PlayerType UnitHolder;
        public int UnitCount;
    }

    public static class ConqueringMiddleEarthStateConfig {
        public String TestName;
        public List<RegionInfo> Config;
        public CurrentGameState ExpectedGameState;

        @Override
        public String toString() {
            return TestName;
        }
    }
}