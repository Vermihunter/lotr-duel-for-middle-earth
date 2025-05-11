package vermesa.lotr.model;

import vermesa.lotr.model.player.Player;

public class GameState {
    Player playerOnMove;
    Player nextPlayerOnMove;

    public GameState(Player playerOnMove, Player nextPlayerOnMove) {
        this.playerOnMove = playerOnMove;
        this.nextPlayerOnMove = nextPlayerOnMove;
    }

    public void setNextPlayerOnMove(Player nextPlayerOnMove) {
        this.nextPlayerOnMove = nextPlayerOnMove;
    }
}
