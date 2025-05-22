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

/**
 * Represents the main object of the game that is being played
 */
public class Game {
    /**
     * Context of the game
     */
    private final GameContext context;

    /**
     * State of the game
     */
    private final GameState state;

    public Game(GameContext context, GameState state) {
        this.context = context;
        this.state = state;
    }


    public final GameState getState() {
        return state;
    }
    public final GameContext getContext() {
        return context;
    }

    /**
     * Returns the moves that the player who is on move has as opportunities
     * - The return value is in groups since there are actions that consist of sub-actions and
     * each sub-action may need a reaction move to it
     *
     * @return Returns a list of Move groups from which the player has to choose one from every group
     */
    public List<List<IMove>> getPossibleMoves() {
        // If there are moves that must be used, return them
        var possibleFollowUpMoves = state.getFollowUpMoves();
        if (possibleFollowUpMoves != null) {
            return possibleFollowUpMoves;
        }

        // Otherwise construct moves for the current player
        ArrayList<IMove> singleMoveGroup = new ArrayList<>();

        addChapterCardMoves(singleMoveGroup);
        addLandmarkTileMoves(singleMoveGroup);

        return List.of(singleMoveGroup);
    }

    /**
     * Function to send moves to the game
     * Note that it receives a List of actions and the reason is that some moves consist
     * of multiple sub-moves and each of them may need a follow-up move to be used.
     *
     * @param moves List of actions to execute
     * @return The result of the move
     */
    public MoveResult makeMove(List<IAction> moves) {
        ActionResult moveRes = IMove.performMultiStageMove(context, state, moves);

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
                if (state.getFollowUpMoves() == null && state.getCurrentGameState() == CurrentGameState.HAS_NOT_ENDED) {
                    throw new IllegalStateException("Game has ended without a winner");
                }
            }

        }

        return new MoveResult(true);
    }

    /**
     * Helper function that appends the playable chapter cards of the current round for the current player
     * Note that not every chapter card is necessarily playable by any player. Any chapter card that is playable
     * can be discarded by any player but some chapter cards require coins to pay to play it and the sentence
     * "playable chapter card" means that it is face up and has no dependencies.
     * @param moves The container to append the chapter card moves to
     */
    private void addChapterCardMoves(ArrayList<IMove> moves) {
        // Helper configuration
        var playableChapterCards = state.getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        Player playerOnMove = state.getPlayerOnMove();
        var playerChainingSymbols = playerOnMove.getPlayerState().getChainingSymbols();

        var playerSkills = playerOnMove.getSkills();
        int playerCoins = playerOnMove.getCoins();

        // Loop over all the playable chapter cards
        for (var playableChapterCardWrapper : playableChapterCards) {
            var playableChapterCard = playableChapterCardWrapper.getChapterCard();

            // Adding the Discard move for each card
            moves.add(new ChapterCardDiscardMove(playableChapterCardWrapper));

            SkillSet requiredSkills = playableChapterCard.context().requiredSkillSet();
            int coinsToPlayCard = playableChapterCard.context().coinsToPlay();
            int skillsMissing = playerSkills.missingSkills(requiredSkills);

            // Check if the player can play the card through chaining symbol
            ChainingSymbols freeToPlayChainingSymbol = playableChapterCard.context().playForFreeChainingSymbol();
            if (freeToPlayChainingSymbol != null && playerChainingSymbols.contains(freeToPlayChainingSymbol)) {
                moves.add(ChapterCardPlayMove.throughChainingSymbols(playableChapterCardWrapper));
            }
            // Check if the player has enough Skills+Coins to play the card
            else if (skillsMissing + coinsToPlayCard <= playerCoins) {
                moves.add(ChapterCardPlayMove.withSkills(playableChapterCardWrapper, coinsToPlayCard + skillsMissing));
            }

        }

    }

    /**
     * Helper function that appends Landmark tile moves for the current round
     * @param moves The container to append the chapter card moves to
     */
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
