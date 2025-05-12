package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Player playerOnMove;
    private Player nextPlayerOnMove;
    private RoundInformation currentRoundInformation;
    private int currentRoundNumber;
    private int totalCoins;
    private List<IAction> followUpMoves;

    public GameState(Player playerOnMove, Player nextPlayerOnMove, int totalCoins) {
        this.playerOnMove = playerOnMove;
        this.nextPlayerOnMove = nextPlayerOnMove;
        this.currentRoundNumber = 0;
        this.totalCoins = totalCoins;
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

    public void initialize(GameContext gameContext) {
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber);
    }

    public Player getPlayerOnMove() {
        return playerOnMove;
    }

    public Player getNextPlayerOnMove() {
        return nextPlayerOnMove;
    }

    void shiftPlayers() {
        Player tmp = playerOnMove;
        playerOnMove = nextPlayerOnMove;
        nextPlayerOnMove = tmp;
    }

    /*
    public void setNextPlayerOnMove(Player nextPlayerOnMove) {
        this.nextPlayerOnMove = nextPlayerOnMove;
    }
     */

    public RoundInformation getCurrentRoundInformation() {
        return currentRoundInformation;
    }

    public void startNewRound(GameContext gameContext) {
        currentRoundNumber++;
        this.currentRoundInformation = gameContext.getRoundInformations().get(currentRoundNumber);
    }
}
