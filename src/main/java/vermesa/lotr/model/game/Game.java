package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.moves.*;
import vermesa.lotr.model.skills.SkillSet;
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

    public final GameState getState() {
        return state;
    }

    /**
     * Returns the moves that the player who is on move has as opportunities
     *
     * @return All valid moves that could be sent view Game.makeMove()
     */
    public List<IMove> getPossibleMoves() {
        // If there are moves that must be used, return them
        var possibleFollowUpMoves = state.getFollowUpMoves();
        if (possibleFollowUpMoves != null) {
            return possibleFollowUpMoves;
        }

        // Otherwise construct moves for the current player
        ArrayList<IMove> possibleMoves = new ArrayList<>();
        addChapterCardMoves(possibleMoves);
        addLandmarkTileMoves(possibleMoves);

        return possibleMoves;
    }

    public MoveResult makeMove(IAction move) {
        ActionResult moveRes = move.action(context, state);

        if (moveRes == null) {
            throw new IllegalArgumentException();
        }

        boolean shiftPlayers = moveRes.shiftNextPlayer();
        boolean followUpActionsEmpty = moveRes.followUpActions().isEmpty();

        if (shiftPlayers && !followUpActionsEmpty) {
            throw new IllegalArgumentException("Cannot shift player while must use follow up actions");
        }

        if (!shiftPlayers && followUpActionsEmpty) {
            throw new IllegalArgumentException("If players are not shifted, there must be follow up actions");
        }

        // Act according to the result of actions
        if (shiftPlayers) {
            state.shiftPlayers();
            state.resetFollowUpMoves();
        } else {
            state.setFollowUpMoves(moveRes.followUpActions());
        }

        // Possibly initialize a new round if there are no more Chapter cards left
        if (state.getCurrentRoundInformation().getChapterCards().getPlayableChapterCards().isEmpty()) {
            if (context.getRoundInformations().size() > state.getCurrentRoundNumber()) {
                state.startNewRound();
            } else {
                if (state.getCurrentGameState() == CurrentGameState.HAS_NOT_ENDED) {
                    throw new IllegalStateException("Game has ended without a winner");
                }
            }

        }

        return new MoveResult(true);
    }

    private void addChapterCardMoves(ArrayList<IMove> moves) {
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
                moves.add(ChapterCardPlayMove.withSkills(playableChapterCard, coinsToPlayCard + skillsMissing));
            }

        }
    }

    private void addLandmarkTileMoves(ArrayList<IMove> moves) {
        Player playerOnMove = state.getPlayerOnMove();
        int alreadyPlacedTowers = (int) context.getCentralBoard().regions().stream()
                .filter(region -> region.getFortress() == playerOnMove)
                .count();

        int generalLandmarkTileCost = alreadyPlacedTowers * context.getLandmarkTileContext().coinPerAlreadyPlacedFortressPawn();
        boolean hasFreeToPlayLandmarkTilesAttribute = playerOnMove.getPlayerState().hasAttribute(RaceEffectAttributes.IGNORE_LANDMARK_TILE_COST);

        // Player does not have the play landmark tiles for free effect unlocked nor
        // does have enough coins to play a landmark tile
        if (!hasFreeToPlayLandmarkTilesAttribute && playerOnMove.getCoins() < generalLandmarkTileCost) {
            return;
        }

        for (var landmarkTile : state.getCurrentlyUsableLandmarkTiles()) {
            // Add landmark tile move with 0 cost if the player has achieved the given effect
            if (hasFreeToPlayLandmarkTilesAttribute) {
                moves.add(new LandmarkTileMove(landmarkTile, 0));
                continue;
            }

            SkillSet requiredSkillset = landmarkTile.requiredSkillset();
            int missingSkills = playerOnMove.getSkills().missingSkills(requiredSkillset);

            // Add landmark tile move if the player has enough coins to play it
            int landmarkTileCost = generalLandmarkTileCost + missingSkills;
            if (landmarkTileCost <= playerOnMove.getCoins()) {
                moves.add(new LandmarkTileMove(landmarkTile, landmarkTileCost));
            }
        }

    }

}
