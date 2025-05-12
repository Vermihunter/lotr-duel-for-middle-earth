package vermesa.lotr.model.game;

import vermesa.lotr.model.player.Player;

public class GameState {
    private Player playerOnMove;
    private Player nextPlayerOnMove;
    private RoundInformation currentRoundInformation;
    private int currentRoundNumber;
    private int totalCoins;

    public GameState(Player playerOnMove, Player nextPlayerOnMove, int totalCoins) {
        this.playerOnMove = playerOnMove;
        this.nextPlayerOnMove = nextPlayerOnMove;
        this.currentRoundNumber = 0;
        this.totalCoins = totalCoins;
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
