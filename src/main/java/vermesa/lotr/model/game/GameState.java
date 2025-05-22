package vermesa.lotr.model.game;

import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet.ChapterCardWrapper;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Game state object that holds the values of the game that do change
 */
public class GameState {
    /**
     * Context of the game - needed for some state manipulation
     */
    private final GameContext gameContext;
    /**
     * The player that has to make the next move
     */
    private Player playerOnMove;
    /**
     * The other player i.e. {@link #playerOnMove} should never equal to {@link #nextPlayerOnMove}
     */
    private Player nextPlayerOnMove;
    /**
     * Information about the current round
     */
    private RoundInformation currentRoundInformation;
    /**
     * Integer representation of the current round - values start from 1
     */
    private int currentRoundNumber;
    /**
     * The total coins that are currently in the reserve
     */
    private int totalCoins;
    /**
     * Cached follow-up moves that the {@link #playerOnMove} has to choose from
     */
    private List<List<IMove>> followUpMoves;
    /**
     * List of landmark tiles that are available to be used
     */
    private ArrayList<LandmarkTile> currentlyUsableLandmarkTiles;

    /**
     * Global index of the landmark tiles since new ones are revealed only after the
     * old ones were used at the beginning of each round
     */
    private int landmarkTileGlobalIndex;

    /**
     * Represents the state in which the game is currently in i.e. whether it
     * has ended or not and if so with what result
     */
    private CurrentGameState currentGameState;

    /**
     * When a chapter card is discarded by {@link vermesa.lotr.model.moves.ChapterCardDiscardMove}, it is placed
     * in this array so it could be played later on in the {@link vermesa.lotr.model.actions.chapter_card_actions.PlayDiscardedChapterCardAction}
     */
    private List<ChapterCardWrapper> discardedChapterCards;

    /**
     * Currently available alliance tokens - the AllianceToken on index 0 is used as the "top-most" alliance token,
     * alliance token with index 1 is under 0 and so on
     */
    private HashMap<Race, ArrayList<AllianceToken>> allianceTokens;

    private GameState(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    /**
     * Adds a chapter card to the discard
     *
     * @param chapterCardWrapper Chapter card that is being added
     */
    public void addChapterCardToDiscard(ChapterCardWrapper chapterCardWrapper) {
        discardedChapterCards.add(chapterCardWrapper);
    }

    /**
     * Returns all the chapter cards that have been discarded during the game
     *
     * @return The discarded chapter cards
     */
    public List<ChapterCardWrapper> getDiscardedChapterCards() {
        return discardedChapterCards;
    }

    /**
     * Removes a landmark tile after it has been used from the currently usable ones
     *
     * @param landmarkTile Landmark tile to remove
     * @return Boolean whether it was present or not - fault detection
     */
    public boolean removeLandmarkTile(LandmarkTile landmarkTile) {
        return currentlyUsableLandmarkTiles.remove(landmarkTile);
    }

    /**
     *
     * @return Currently usable landmark tiles
     */
    public List<LandmarkTile> getCurrentlyUsableLandmarkTiles() {
        return currentlyUsableLandmarkTiles;
    }

    /**
     *
     * @return Current game state
     */
    public CurrentGameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     *
     * @return Follow-up moves
     */
    public List<List<IMove>> getFollowUpMoves() {
        return followUpMoves;
    }

    /**
     * Set follow-up moves after a move has been made
     * @param followUpMoves Follow-up moves that the next player has to choose from
     */
    public void setFollowUpMoves(List<List<IMove>> followUpMoves) {
        this.followUpMoves = followUpMoves;
    }

    /**
     * If the current moves have no more follow-up moves, it is reset so new
     * moves are generated for the next call
     */
    public void resetFollowUpMoves() {
        followUpMoves = null;
    }

    /**
     * Puts back coins to the reserve
     * @param coins Number of coins to put back
     */
    public void putBackCoinsToReserve(int coins) {
        totalCoins += coins;
    }

    /**
     * Takes coin from the reserve
     * @param coins Number of coins to take
     */
    public void takeCoinsFromReserve(int coins) {
        totalCoins -= coins;
    }

    /**
     *
     * @return The total number of coins currently present in the reserve
     */
    public int getTotalCoins() {
        return totalCoins;
    }

    /**
     *
     * @return The player that has to make the next move
     */
    public Player getPlayerOnMove() {
        return playerOnMove;
    }

    /**
     *
     * @return The other player
     */
    public Player getNextPlayerOnMove() {
        return nextPlayerOnMove;
    }

    /**
     * @return The number of the current round
     */
    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    /**
     * @return Information about the current round
     */
    public RoundInformation getCurrentRoundInformation() {
        return currentRoundInformation;
    }

    /**
     * Checks if the game has already ended and if not, shifts players i.e. the player that
     * played the previous move will become the nextPlayerOnMove and vica-versa
     */
    public void shiftPlayers() {
        currentGameState = checkGameState();
        if (currentGameState != CurrentGameState.HAS_NOT_ENDED) {
            return;
        }

        Player tmp = playerOnMove;
        playerOnMove = nextPlayerOnMove;
        nextPlayerOnMove = tmp;
    }

    /**
     * Evaluates the current game state i.e. the fact whether someone
     * has won the game or it has not ended yet
     *
     * @return The game state stating whether someone has won the game
     */
    private CurrentGameState checkGameState() {
        // Collect win conditions
        CurrentGameState[] winConditions = new CurrentGameState[]{
                checkQuestOfTheRingState(),
                checkSupportOfTheRacesState(),
                checkConqueringMiddleEarthState()
        };

        // Go through win conditions, if any of them is met, return -→ someone has won the game
        for (CurrentGameState currentGameState : winConditions) {
            if (currentGameState != CurrentGameState.HAS_NOT_ENDED) {
                return currentGameState;
            }
        }

        // Nobody won → the game has not ended
        return CurrentGameState.HAS_NOT_ENDED;
    }

    /**
     * Checks if any of the player have conquered Middle-earth and has won the game
     * @return A game stat representing the fact that a player has won the game or not
     */
    private CurrentGameState checkConqueringMiddleEarthState() {
        long fellowShipPlayerPresentInRegions = playerPresentInRegions(gameContext.getFellowshipPlayer());
        long sauronPlayerPresentInRegions = playerPresentInRegions(gameContext.getSauronPlayer());
        var regions = gameContext.getCentralBoard().regions();

        // Fellowship player has conquered all regions -> won the game
        if (fellowShipPlayerPresentInRegions == regions.size()) {
            return CurrentGameState.FELLOWSHIP_WON;
        }

        // Sauron player has conquered all regions -> won the game
        if (sauronPlayerPresentInRegions == regions.size()) {
            return CurrentGameState.SAURON_WON;
        }

        // The game has ended, the winner/draw is decided according to the regions conquered by players
        if (currentRoundNumber == gameContext.getRoundInformations().size() // last round
                && currentRoundInformation.getChapterCards().getPlayableChapterCards().isEmpty()) // and no more chapter cards = round has ended
        {
            // Sauron player has more regions -> won
            if (sauronPlayerPresentInRegions > fellowShipPlayerPresentInRegions) {
                return CurrentGameState.SAURON_WON;
            }
            // Fellowship player has more regions -> won
            else if (fellowShipPlayerPresentInRegions > sauronPlayerPresentInRegions) {
                return CurrentGameState.FELLOWSHIP_WON;
            }

            // The same amount of regions are conquered by both players -> draw
            return CurrentGameState.DRAW;

        }

        return CurrentGameState.HAS_NOT_ENDED;
    }

    /**
     * Returns the number of regions that the given player is present in either with units or with a fortress
     * @param player The player to check the regions for
     * @return The number of regions the player is present in
     */
    private long playerPresentInRegions(Player player) {
        var regions = gameContext.getCentralBoard().regions();
        return regions.stream()
                .filter(region -> region.getFortress() == player || region.getUnit() == player)
                .count();
    }


    /**
     * Checks if any of the players have won the game through having the support of enough races
     * @return Game state
     */
    private CurrentGameState checkSupportOfTheRacesState() {
        if (playerHasSupportOfTheRaces(gameContext.getFellowshipPlayer())) {
            return CurrentGameState.FELLOWSHIP_WON;
        }

        if (playerHasSupportOfTheRaces(gameContext.getSauronPlayer())) {
            return CurrentGameState.SAURON_WON;
        }

        return CurrentGameState.HAS_NOT_ENDED;
    }

    /**
     * Checks whether the player has support of 6 races or not
     * @param player The player to check the support of races for
     * @return Boolean value whether the player has enough supporting races or not
     */
    private boolean playerHasSupportOfTheRaces(Player player) {
        return Arrays.stream(player.getSupportingRaces())
                .filter(s -> s > 0)
                .count() >= 6;
    }

    /**
     * Checks if any of the players has won the game through the quest of the ring track
     * - If the Sauron player has caught the fellowship player
     * - If the Fellowship player ha reached Mount Doom
     * @return The game state according to the quest of the ring track
     */
    private CurrentGameState checkQuestOfTheRingState() {
        var questOfTheRingTrack = gameContext.getQuestOfTheRingTrack();
        int fellowShipInd = questOfTheRingTrack.getFellowshipPlayerIndex();
        int sauronInd = questOfTheRingTrack.getSauronPlayerIndex();
        int width = questOfTheRingTrack.getWidth();

        if (fellowShipInd == width) {
            return CurrentGameState.FELLOWSHIP_WON;
        }

        if (sauronInd >= fellowShipInd) {
            return CurrentGameState.SAURON_WON;
        }

        return CurrentGameState.HAS_NOT_ENDED;
    }


    /**
     * Starts a new round and shifts the information
     * Also adds new landmark tiles if the old ones were used up to {@link vermesa.lotr.model.landmark_effects.LandmarkTileContext#landmarkTilesAtTime()}
     */
    public void startNewRound() {
        currentRoundNumber++;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber - 1);


        // Add missing landmark tiles
        var landmarkTiles = gameContext.getLandmarkTiles();
        int landmarkTilesAtTheSameTime = gameContext.getLandmarkTileContext().landmarkTilesAtTime();
        int landmarkTilesToPlace = landmarkTilesAtTheSameTime - currentlyUsableLandmarkTiles.size();
        for (int i = 0; i < landmarkTilesToPlace; i++) {
            currentlyUsableLandmarkTiles.add(landmarkTiles.get(landmarkTileGlobalIndex));
            landmarkTileGlobalIndex++;
        }
    }

    /**
     * Removes an alliance token from the currently available alliance tokens
     *
     * @param allianceToken Alliance token that is being removed
     */
    public void removeAllianceTokens(AllianceToken allianceToken) {
        for (var raceAllianceTokens : allianceTokens.values()) {
            if (raceAllianceTokens.remove(allianceToken)) {
                return;
            }
        }
    }

    public HashMap<Race, ArrayList<AllianceToken>> getAllianceTokens() {
        return allianceTokens;
    }

    /**
     * Since the game has many rules that must hold and the game state object has grown,
     * it is more safe to create a Builder for it that ensure correct building of the object
     */
    public static final class GameStateBuilder {
        private Player playerOnMove;
        private Player nextPlayerOnMove;
        private RoundInformation currentRoundInformation;
        private int currentRoundNumber;
        private int totalCoins;
        private List<List<IMove>> followUpMoves;
        private GameContext gameContext;
        private CurrentGameState currentGameState;
        private ArrayList<LandmarkTile> currentlyUsableLandmarkTiles;
        private HashMap<Race, ArrayList<AllianceToken>> allianceTokens;

        private GameStateBuilder() {
        }

        public static GameStateBuilder aGameState() {
            return new GameStateBuilder();
        }

        public GameStateBuilder withPlayerOnMove(Player playerOnMove) {
            this.playerOnMove = playerOnMove;
            return this;
        }

        public GameStateBuilder withNextPlayerOnMove(Player nextPlayerOnMove) {
            this.nextPlayerOnMove = nextPlayerOnMove;
            return this;
        }

        public GameStateBuilder withCurrentRoundInformation(RoundInformation currentRoundInformation) {
            this.currentRoundInformation = currentRoundInformation;
            return this;
        }

        public GameStateBuilder withCurrentRoundNumber(int currentRoundNumber) {
            this.currentRoundNumber = currentRoundNumber;
            return this;
        }

        public GameStateBuilder withTotalCoins(int totalCoins) {
            this.totalCoins = totalCoins;
            return this;
        }

        public GameStateBuilder withFollowUpMoves(List<List<IMove>> followUpMoves) {
            this.followUpMoves = followUpMoves;
            return this;
        }

        public GameStateBuilder withGameContext(GameContext gameContext) {
            this.gameContext = gameContext;
            return this;
        }

        public GameStateBuilder withCurrentGameState(CurrentGameState currentGameState) {
            this.currentGameState = currentGameState;
            return this;
        }

        public GameStateBuilder withStartingLandmarkTiles(ArrayList<LandmarkTile> currentlyUsableLandmarkTiles) {
            this.currentlyUsableLandmarkTiles = currentlyUsableLandmarkTiles;
            return this;
        }

        public GameStateBuilder withAllianceTokens(HashMap<Race, ArrayList<AllianceToken>> allianceTokens) {
            this.allianceTokens = allianceTokens;
            return this;
        }

        public GameState build() {
            GameState gameState = new GameState(gameContext);
            gameState.setFollowUpMoves(followUpMoves);
            gameState.currentRoundInformation = this.currentRoundInformation;
            gameState.nextPlayerOnMove = this.nextPlayerOnMove;
            gameState.currentRoundNumber = this.currentRoundNumber;
            gameState.totalCoins = this.totalCoins;
            gameState.currentGameState = this.currentGameState;
            gameState.playerOnMove = this.playerOnMove;
            gameState.currentlyUsableLandmarkTiles = this.currentlyUsableLandmarkTiles;
            gameState.allianceTokens = this.allianceTokens;
            gameState.landmarkTileGlobalIndex = gameContext.getLandmarkTileContext().landmarkTilesAtTime();
            gameState.discardedChapterCards = new ArrayList<>();

            return gameState;
        }
    }
}
