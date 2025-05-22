package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet.ChapterCardWrapper;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;

/**
 * A move that chooses a chapter card and discards it
 * - As a side effect, it is put into the discarded card container through {@link GameState#addChapterCardToDiscard(ChapterCardWrapper)}
 * - It costs nothing to play this move
 */
public class ChapterCardDiscardMove extends ChapterCardMove implements Serializable {

    public ChapterCardDiscardMove(ChapterCardWrapper chapterCard) {
        super(chapterCard);
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // Action
        int coinsPerDiscardedCardsInCurrRound = state.getCurrentRoundInformation().getCoinsPerDiscardedCards();
        state.getPlayerOnMove().addCoins(coinsPerDiscardedCardsInCurrRound);

        // Send CHAPTER_CARD_DISCARDED signal
        Player playerOnMove = state.getPlayerOnMove();
        var callbackHandler = playerOnMove.getPlayerState().getRaceEffectCallbackEventHandler();
        callbackHandler.signalEvent(RaceEffectCallbackEventType.CHAPTER_CARD_DISCARDED, ctx, state);

        // Add chapter card to teh discard
        state.addChapterCardToDiscard(chapterCard);

        // Send move successful to chapter card set to handle next state
        onSuccessfulMove(state);

        return ActionResult.OK;
    }
}
