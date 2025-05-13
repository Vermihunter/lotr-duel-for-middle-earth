package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.Arrays;
import java.util.List;

public class GameState {
    private Player playerOnMove;
    private Player nextPlayerOnMove;
    private RoundInformation currentRoundInformation;
    private int currentRoundNumber;
    private int totalCoins;
    private List<IMove> followUpMoves;
    private List<LandmarkTile> currentlyUsableLandmarkTiles;
    private int landmarkTileGlobalIndex;
    private final GameContext gameContext;
    private CurrentGameState currentGameState;

    public GameState(Player playerOnMove, Player nextPlayerOnMove, int totalCoins, GameContext gameContext, List<LandmarkTile> currentlyUsableLandmarkTiles) {
        this.playerOnMove = playerOnMove;
        this.nextPlayerOnMove = nextPlayerOnMove;
        this.gameContext = gameContext;
        this.totalCoins = totalCoins;
        this.currentRoundNumber = 1;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber - 1);
        this.currentGameState = checkGameState();
        this.currentlyUsableLandmarkTiles = currentlyUsableLandmarkTiles;
        this.landmarkTileGlobalIndex = this.currentlyUsableLandmarkTiles.size();
    }

    public boolean removeLandmarkTile(LandmarkTile landmarkTile) {
        return currentlyUsableLandmarkTiles.remove(landmarkTile);
    }

    public List<LandmarkTile> getCurrentlyUsableLandmarkTiles() {
        return currentlyUsableLandmarkTiles;
    }

    public CurrentGameState getCurrentGameState() {
        return currentGameState;
    }

    public List<IMove> getFollowUpMoves() {
        return followUpMoves;
    }

    public void setFollowUpMoves(List<IMove> followUpMoves) {
        this.followUpMoves = followUpMoves;
    }

    public void resetFollowUpMoves() {
        followUpMoves = null;
    }


    public void putBackCoinsToReserve(int coins) {
        totalCoins += coins;
    }

    public void takeCoinsFromReserve(int coins) {
        totalCoins -= coins;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public Player getPlayerOnMove() {
        return playerOnMove;
    }

    public Player getNextPlayerOnMove() {
        return nextPlayerOnMove;
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


    private long playerPresentInRegions(Player player) {
        var regions = gameContext.getCentralBoard().regions();
        return regions.stream()
                .filter(region -> region.getFortress() == player || region.getUnit() == player)
                .count();
    }

    private CurrentGameState checkSupportOfTheRacesState() {
        if (playerHasSupportOfTheRaces(gameContext.getFellowshipPlayer())) {
            return CurrentGameState.FELLOWSHIP_WON;
        }

        if (playerHasSupportOfTheRaces(gameContext.getSauronPlayer())) {
            return CurrentGameState.SAURON_WON;
        }

        return CurrentGameState.HAS_NOT_ENDED;
    }

    private boolean playerHasSupportOfTheRaces(Player player) {
        return Arrays.stream(player.getSupportingRaces())
                .filter(s -> s > 0)
                .count() >= 6;
    }

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

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public RoundInformation getCurrentRoundInformation() {
        return currentRoundInformation;
    }

    public void startNewRound() {
        currentRoundNumber++;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber - 1);
    }
}
