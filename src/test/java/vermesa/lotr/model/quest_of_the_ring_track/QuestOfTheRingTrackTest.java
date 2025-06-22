package vermesa.lotr.model.quest_of_the_ring_track;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import vermesa.lotr.model.central_board.DefaultRegionBuilder;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.DefaultGameContextBuilder;
import vermesa.lotr.model.game.DefaultGameStateBuilder;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.PlayerType;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuestOfTheRingTrackTest {
    GameState gameState;
    GameContext gameContext;

    QuestOfTheRingTrack questOfTheRingTrack;

    @BeforeEach
    void setUp() {
        Random nonRandom = new Random(1);
        gameContext = DefaultGameContextBuilder.buildDefaultGameContext(nonRandom, DefaultGameContextBuilder.buildDefaultBuilderConfig());
        gameState = DefaultGameStateBuilder.buildDefaultGameState(nonRandom, gameContext);

        questOfTheRingTrack = DefaultQuestOfTheRingTrackCreator.createQuestOfTheRingTrack(gameContext.getCentralBoard().regions());
    }

    @ParameterizedTest(name = "[{index}] - Move count: {0}")
    @ValueSource(ints = {0, 1, 2, 3})
    void movePlayer_fellowshipIndexTest(int moveCount) {
        int initialFellowshipIndex = questOfTheRingTrack.getFellowshipPlayerIndex();
        int initialSauronIndex = questOfTheRingTrack.getSauronPlayerIndex();

        // Act
        questOfTheRingTrack.movePlayer(PlayerType.Fellowship, moveCount, gameContext, gameState);

        // Assert → both indices move
        assertEquals(initialFellowshipIndex + moveCount, questOfTheRingTrack.getFellowshipPlayerIndex());
        assertEquals(initialSauronIndex + moveCount, questOfTheRingTrack.getSauronPlayerIndex());
    }

    @ParameterizedTest(name = "[{index}] - Move count: {0}")
    @ValueSource(ints = {0, 1, 2, 3})
    void movePlayer_sauronIndexTest(int moveCount) {
        int initialFellowshipIndex = questOfTheRingTrack.getFellowshipPlayerIndex();
        int initialSauronIndex = questOfTheRingTrack.getSauronPlayerIndex();

        // Act
        questOfTheRingTrack.movePlayer(PlayerType.Sauron, moveCount, gameContext, gameState);

        // Assert → only sauron index move
        assertEquals(initialFellowshipIndex, questOfTheRingTrack.getFellowshipPlayerIndex());
        assertEquals(initialSauronIndex + moveCount, questOfTheRingTrack.getSauronPlayerIndex());
    }

    @Test
    void movePlayer_SauronPlayer_bonusActionAcquiredTest() {
        var bonusActions = questOfTheRingTrack.getBonusActions();
        int initialSauronIndex = questOfTheRingTrack.getSauronPlayerIndex();
        int betweenFirstTwoBonusesIndex = bonusActions.getFirst().pos();

        // Act
        questOfTheRingTrack.movePlayer(PlayerType.Sauron, betweenFirstTwoBonusesIndex, gameContext, gameState);

        // Assert → how?

    }

    /*
    @Test
    void movePlayer_FellowshipPlayer_bonusActionAcquiredTest() {
        var bonusActions = questOfTheRingTrack.getBonusActions();


    }
     */
}