package vermesa.lotr.model;

import vermesa.lotr.model.player.Player;

public interface IMove {
    public boolean isPlayableByPlayer(Player player);
    public MoveResult playMove(GameContext ctx, GameState state);
}
