package vermesa.lotr.model;

import java.util.ArrayList;

public class Game {
    private final GameContext context;
    private final GameState state;

    public Game(GameContext context, GameState state) {
        this.context = context;
        this.state = state;
    }

    public ArrayList<IMove> getPossibleMoves() {
        return null;
    }

    public MoveResult makeMove(IMove move) {
        return null;
    }
}
