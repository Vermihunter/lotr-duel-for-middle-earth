package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet.ChapterCardWrapper;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerState;

import java.io.Serializable;

/**
 * Since the actual actions that are executed in a Chapter card move are configured
 * in the ChapterCardContext object as a list of IAction objects, all chapter card play
 * moves can be represented by a single class
 */
public class ChapterCardPlayMove extends ChapterCardMove implements Serializable {
    private final boolean addedThroughChaining;
    private final int coinsToPlay;

    private ChapterCardPlayMove(ChapterCardWrapper chapterCard, boolean addedThroughChaining, int coinsToPlay) {
        super(chapterCard);
        this.addedThroughChaining = addedThroughChaining;
        this.coinsToPlay = coinsToPlay;
    }

    /**
     * Factory method to create a ChapterCardPlayMove if the player has enough skills+coins
     * to pay for playing this card
     *
     * @param chapterCard The chapter card that will be played
     * @param coinsToPlay The number of coins that costs playing this card
     * @return The ChapterCardPlayMove object wrapping the chapter card
     */
    public static ChapterCardPlayMove withSkills(ChapterCardWrapper chapterCard, int coinsToPlay) {
        return new ChapterCardPlayMove(chapterCard, false, coinsToPlay);
    }

    /**
     * Factory method to create a ChapterCardPlayMove through chaining symbols i.e. for free
     * A move is constructed this way if the player has the chaining symbol {@link vermesa.lotr.model.chapter_cards.ChapterCardContext#playForFreeChainingSymbol()}
     * between {@link PlayerState#getChainingSymbols()}
     *
     * @param chapterCard The chapter card that will be played
     * @return The ChapterCardPlayMove object wrapping the chapter card
     */
    public static ChapterCardPlayMove throughChainingSymbols(ChapterCardWrapper chapterCard) {
        return new ChapterCardPlayMove(chapterCard, true, 0);
    }

    /**
     * Factory method to create a ChapterCardPlayMove for playing it from the discarded chapter cards
     *
     * @param chapterCard The chapter card that will be played
     * @return The ChapterCardPlayMove object wrapping the chapter card
     */
    public static ChapterCardPlayMove fromDiscard(ChapterCardWrapper chapterCard) {
        return new ChapterCardPlayMove(chapterCard, false, 0);
    }

    /**
     * Executes all the sub-actions that are configured for the chapter card
     * If any of the sub-actions returns that the round should be not shifted,
     * or there are must-be-used follow-up actions, they will be set as the return
     * of this function
     * <p>
     * After executing the sub-actions, this function calls the event signaller
     *
     * @param ctx   Game context
     * @param state Game state
     * @return Aggregated ActionResult across all the sub-actions
     */
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // Perform common implementation of multi-stage move
        ActionResult result = IMove.performMultiStageMove(ctx, state, chapterCard.getChapterCard().context().actions());
        Player playerOnMove = state.getPlayerOnMove();

        // Adding chaining symbols if Chapter card contains any
        ChainingSymbols chapterCardChainingSymbols = chapterCard.getChapterCard().context().gainedChainingSymbol();
        if (chapterCardChainingSymbols != null) {
            playerOnMove.getPlayerState().addChainingSymbol(chapterCardChainingSymbols);
        }

        // Signal event handler for current Chapter card by color
        var raceEffectCallbackEventType = toRaceEffectCallbackEventType();
        var callbackHandler = playerOnMove.getPlayerState().getRaceEffectCallbackEventHandler();
        if (raceEffectCallbackEventType != null) {
            callbackHandler.signalEvent(raceEffectCallbackEventType, ctx, state);
        }

        // Signal event handler for current Chapter card if added through chaining symbols
        if (addedThroughChaining) {
            callbackHandler.signalEvent(RaceEffectCallbackEventType.CHAINING_SYMBOL_USED, ctx, state);
        }

        // Common things on successful Chapter card moves
        onSuccessfulMove(state);

        // Pay the price for playing the card
        extractCoins(ctx, state, coinsToPlay);

        return result;
    }

    /**
     * Gets the Race effect callback event type for a given Chapter card according to its Color
     * Note that there is no current event for Grey and Purple Chapter cards
     *
     * @return Callback event type that is passed to the signaller
     */
    RaceEffectCallbackEventType toRaceEffectCallbackEventType() {
        return switch (chapterCard.getChapterCard().context().color()) {
            case BLUE -> RaceEffectCallbackEventType.CHAPTER_CARD_BLUE_USED;
            case GREEN -> RaceEffectCallbackEventType.CHAPTER_CARD_GREEN_USED;
            case YELLOW -> RaceEffectCallbackEventType.CHAPTER_CARD_YELLOW_USED;
            default -> null;
        };
    }

    @Override
    public int coinsToPlay() {
        return coinsToPlay;
    }
}
