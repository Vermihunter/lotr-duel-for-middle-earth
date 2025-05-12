package vermesa.lotr.model.moves;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.chapter_cards.ChapterCard;

public class ChapterCardDiscardMove extends ChapterCardMove {

    public ChapterCardDiscardMove(ChapterCard chapterCard) {
        super(chapterCard);
    }

    @Override
    public MoveResult playMove(GameContext ctx, GameState state) {
        int coinsPerDiscardedCardsInCurrRound = state.getCurrentRoundInformation().getCoinsPerDiscardedCards();
        state.getPlayerOnMove().addCoins(coinsPerDiscardedCardsInCurrRound);

        // TODO: move result
        return new MoveResult();
    }
}
