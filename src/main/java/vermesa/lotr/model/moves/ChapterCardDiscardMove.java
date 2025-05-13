package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;

public class ChapterCardDiscardMove extends ChapterCardMove implements Serializable {

    public ChapterCardDiscardMove(ChapterCard chapterCard) {
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

        // Send move successful to chapter card set to handle next state
        onSuccessfulMove(state);

        return ActionResult.OK;
    }

    @Override
    public int coinsToPlay() {
        return 0;
    }
}
