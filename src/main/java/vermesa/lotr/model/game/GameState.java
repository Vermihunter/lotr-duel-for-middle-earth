package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.CentralBoard;
import vermesa.lotr.model.player.Player;

import java.util.Arrays;
import java.util.List;

public class GameState {
    private Player playerOnMove;
    private Player nextPlayerOnMove;
    private RoundInformation currentRoundInformation;
    private int currentRoundNumber;
    private int totalCoins;
    private List<IAction> followUpMoves;
    private final GameContext gameContext;
    private CurrentGameState currentGameState;

    public GameState(Player playerOnMove, Player nextPlayerOnMove, int totalCoins, GameContext gameContext) {
        this.playerOnMove = playerOnMove;
        this.nextPlayerOnMove = nextPlayerOnMove;
        this.gameContext = gameContext;
        this.totalCoins = totalCoins;
        this.currentRoundNumber = 0;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber);
    }

    public CurrentGameState getCurrentGameState() {
        return currentGameState;
    }

    public List<IAction> getFollowUpMoves() {
        return followUpMoves;
    }

    public void setFollowUpMoves(List<IAction> followUpMoves) {
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

    public void shiftPlayers() {
        currentGameState = checkGameState();
        if (currentGameState != CurrentGameState.HAS_NOT_ENDED) {
            return;
        }

        Player tmp = playerOnMove;
        playerOnMove = nextPlayerOnMove;
        nextPlayerOnMove = tmp;
    }

    private CurrentGameState checkGameState() {
        CurrentGameState questOfTheRingState = checkQuestOfTheRingState();
        CurrentGameState supportOfTheRacesState = checkSupportOfTheRacesState();
        CurrentGameState conqueringMiddleEarthState = checkConqueringMiddleEarthState();

        return null;
    }

    private CurrentGameState checkConqueringMiddleEarthState() {
        if (playerPresentInAllRegions(gameContext.getFellowshipPlayer())) {
            return CurrentGameState.FELLOWSHIP_WON;
        }

        if (playerPresentInAllRegions(gameContext.getSauronPlayer())) {
            return CurrentGameState.SAURON_WON;
        }

        return CurrentGameState.HAS_NOT_ENDED;
    }

    private boolean playerPresentInAllRegions(Player player) {
        var regions = gameContext.getCentralBoard().regions();
        return regions.stream()
                .filter(region -> region.getFortress() == player || region.getUnit() == player)
                .count() == regions.size();
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

    public RoundInformation getCurrentRoundInformation() {
        return currentRoundInformation;
    }

    public void startNewRound() {
        currentRoundNumber++;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber);
    }
}
