package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Since the actual actions that are executed in a Chapter card move are configured
 * in the ChapterCardContext object as a list of IAction objects, all chapter card play
 * moves can be represented by a single class
 */
public class ChapterCardPlayMove extends ChapterCardMove {
    private final boolean addedThroughChaining;

    private ChapterCardPlayMove(ChapterCard chapterCard, boolean addedThroughChaining) {
        super(chapterCard);
        this.addedThroughChaining = addedThroughChaining;
    }

    public static ChapterCardPlayMove withSkills(ChapterCard chapterCard) {
        return new ChapterCardPlayMove(chapterCard, false);
    }

    public static ChapterCardPlayMove throughChainingSymbols(ChapterCard chapterCard) {
        return new ChapterCardPlayMove(chapterCard, true);
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
        boolean shiftRounds = true;
        List<IAction> followUpActions = List.of();
        Player playerOnMove = state.getPlayerOnMove();

        // Executing sub-actions
        for (var action : chaptercard.context().actions()) {
            var subResult = action.action(ctx, state);

            if (!subResult.shiftNextPlayer()) {
                shiftRounds = false;
            }

            if (!subResult.followUpActions().isEmpty()) {
                if (followUpActions.isEmpty()) {
                    throw new IllegalArgumentException("There were already defined follow up actions - conflict");
                }
                followUpActions = subResult.followUpActions();
            }
        }

        // Adding chaining symbols if Chapter card contains any
        ChainingSymbols chapterCardChainingSymbols = chaptercard.context().gainedChainingSymbol();
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

        return new ActionResult(followUpActions, shiftRounds);
    }

    /**
     * Gets the Race effect callback event type for a given Chapter card according to its Color
     * Note that there is no current event for Grey and Purple Chapter cards
     *
     * @return Callback event type that is passed to the signaller
     */
    RaceEffectCallbackEventType toRaceEffectCallbackEventType() {
        return switch (chaptercard.context().color()) {
            case BLUE -> RaceEffectCallbackEventType.CHAPTER_CARD_BLUE_USED;
            case GREEN -> RaceEffectCallbackEventType.CHAPTER_CARD_GREEN_USED;
            case RED -> RaceEffectCallbackEventType.CHAPTER_CARD_RED_USED;
            case YELLOW -> RaceEffectCallbackEventType.CHAPTER_CARD_YELLOW_USED;
            default -> null;
        };
    }
}
