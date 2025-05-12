package vermesa.lotr.model.moves;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;

public interface IMove {
    //public boolean isPlayableByPlayer(Player player);
    public MoveResult playMove(GameContext ctx, GameState state);
}
