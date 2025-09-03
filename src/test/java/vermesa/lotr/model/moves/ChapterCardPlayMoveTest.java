package vermesa.lotr.model.moves;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventHandler;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;
import vermesa.lotr.model.game.DefaultGameBuilder;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerState;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChapterCardPlayMoveTest {


    @Mock
    GameContext ctx;

    @Mock
    GameState state;

    @Mock
    Player playerOnMove;

    @Mock
    PlayerState playerState;

    @Mock
    RaceEffectCallbackEventHandler callbackHandler;

    @Mock
    RoundChapterCardSet.ChapterCardWrapper chapterCard;

    // we’ll need to mock out the static multi‐stage move method:
    MockedStatic<IMove> imoveStatic;

    //ChapterCardPlayMove actionUnderTest;

    @Test
    void action_RemovedFromPlayableCards() {
        // Arrange
        var game = DefaultGameBuilder.buildDefaultGame(new Random(1));
        var playableChapterCards = game.state().getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        var playableChapterCard = playableChapterCards.getFirst();

        var chapterCardPlayMove = ChapterCardPlayMove.withSkills(playableChapterCard, 0);


        // Act
        chapterCardPlayMove.action(game.context(), game.state());


        // Assert
        assertFalse(playableChapterCards.contains(playableChapterCard));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void action_CoinsExtracted(int cost) {
        // Arrange
        var game = DefaultGameBuilder.buildDefaultGame(new Random(1));
        var playableChapterCards = game.state().getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        var playableChapterCard = playableChapterCards.getFirst();
        var chapterCardPlayMove = ChapterCardPlayMove.withSkills(playableChapterCard, cost);

        int playerCoinsBefore = game.state().getPlayerOnMove().getCoins();

        // Act
        chapterCardPlayMove.action(game.context(), game.state());


        // Assert
        assertEquals(playerCoinsBefore, game.state().getPlayerOnMove().getCoins() + cost);
    }

    @Test
    void action_ChainingSymbolEventHandlerCalled() {
        // Arrange
        var game = DefaultGameBuilder.buildDefaultGame(new Random(1));
        var playableChapterCards = game.state().getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        var playableChapterCard = playableChapterCards.getFirst();
        var chapterCardPlayMove = ChapterCardPlayMove.throughChainingSymbols(playableChapterCard);


        // Act
        chapterCardPlayMove.action(game.context(), game.state());


        // Assert
        assertFalse(playableChapterCards.contains(playableChapterCard));
    }


    //@BeforeEach
    /*
    void setUp() {
        // 1) Wire up the GameState → Player → PlayerState → EventHandler

        when(state.getPlayerOnMove()).thenReturn(playerOnMove);
        when(playerOnMove.getPlayerState()).thenReturn(playerState);
        when(playerState.getRaceEffectCallbackEventHandler()).thenReturn(callbackHandler);

        // 2) Stub the ChapterCard to return no extra chaining symbols
        when(chapterCard.getChapterCard()
                .context()
                .gainedChainingSymbol())
                .thenReturn(null);

        // 3) Stub the static IMove.performMultiStageMove(...) so we don’t need its real logic
        imoveStatic = mockStatic(IMove.class);
        imoveStatic
                .when(() -> IMove.performMultiStageMove(eq(ctx), eq(state), any()))
                .thenReturn(ActionResult.OK);

        // 4) Create your action, force addedThroughChaining = true
        ChapterCardPlayMove actionUnderTest = ChapterCardPlayMove.throughChainingSymbols(chapterCard);
    }

    @Test
    void whenAddedThroughChaining_thenSignalChainingSymbolEvent() {
        // act
        ChapterCardPlayMove actionUnderTest = ChapterCardPlayMove.throughChainingSymbols(chapterCard);
        ActionResult result = actionUnderTest.action(ctx, state);

        // assert: static move was invoked
        imoveStatic.verify(
                () -> IMove.performMultiStageMove(eq(ctx), eq(state), any()), times(1));

        // assert: callbackHandler.signalEvent(...) for CHAINING_SYMBOL_USED was called
        verify(callbackHandler, times(1))
                .signalEvent(RaceEffectCallbackEventType.CHAINING_SYMBOL_USED, ctx, state);

        // no unexpected interactions
        verifyNoMoreInteractions(callbackHandler);
        imoveStatic.close();
    }

*/
}