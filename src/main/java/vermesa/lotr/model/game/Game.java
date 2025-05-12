package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.moves.ChapterCardDiscardMove;
import vermesa.lotr.model.moves.ChapterCardPlayMove;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameContext context;
    private final GameState state;

    public Game(GameContext context, GameState state) {
        this.context = context;
        this.state = state;
    }

    /**
     * Returns the moves that the player who is on move has as opportunities
     *
     * @return All valid moves that could be sent view Game.makeMove()
     */
    public List<IAction> getPossibleMoves() {
        // If there are moves that must be used, return them
        var possibleFollowUpMoves = state.getFollowUpMoves();
        if (possibleFollowUpMoves != null) {
            return possibleFollowUpMoves;
        }

        // Otherwise construct moves for the current player
        ArrayList<IAction> possibleMoves = new ArrayList<>();
        addChapterCardMoves(possibleMoves);
        addLandmarkTileMoves(possibleMoves);

        return possibleMoves;
    }

    public MoveResult makeMove(IAction move) {
        ActionResult moveRes = move.action(context, state);

        boolean shiftPlayers = moveRes.shiftNextPlayer();
        boolean followUpActionsEmpty = moveRes.followUpActions().isEmpty();

        if (shiftPlayers && !followUpActionsEmpty) {
            throw new IllegalArgumentException("Cannot shift player while must use follow up actions");
        }

        if (!shiftPlayers && followUpActionsEmpty) {
            throw new IllegalArgumentException("If players are not shifted, there must be follow up actions");
        }

        if (shiftPlayers) {
            state.shiftPlayers();
        } else {
            state.setFollowUpMoves(moveRes.followUpActions());
        }

        return new MoveResult(true);
    }

    private void addChapterCardMoves(ArrayList<IAction> moves) {
        var playableChapterCards = state.getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        Player playerOnMove = state.getPlayerOnMove();
        var playerChainingSymbols = playerOnMove.getPlayerState().getChainingSymbols();

        var playerSkills = playerOnMove.getSkills();
        int playerCoins = playerOnMove.getCoins();

        for (var playableChapterCard : playableChapterCards) {
            // Adding the Discard move for each card
            moves.add(new ChapterCardDiscardMove(playableChapterCard));

            SkillSet requiredSkills = playableChapterCard.context().requiredSkillSet();
            int coinsToPlayCard = playableChapterCard.context().coinsToPlay();
            int skillsMissing = playerSkills.missingSkills(requiredSkills);

            // Check if the player can play the card through chaining symbol
            ChainingSymbols freeToPlayChainingSymbol = playableChapterCard.context().playForFreeChainingSymbol();
            if (freeToPlayChainingSymbol != null && playerChainingSymbols.contains(freeToPlayChainingSymbol)) {
                moves.add(ChapterCardPlayMove.throughChainingSymbols(playableChapterCard));
            }
            // Check if the player has enough Skills+Coins to play the card
            else if (skillsMissing + coinsToPlayCard <= playerCoins) {
                moves.add(ChapterCardPlayMove.withSkills(playableChapterCard));
            }

        }
    }

    private void addLandmarkTileMoves(ArrayList<IAction> moves) {

    }

}
