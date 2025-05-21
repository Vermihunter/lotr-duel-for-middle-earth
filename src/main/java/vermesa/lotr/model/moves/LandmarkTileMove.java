package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.player.Player;

public record LandmarkTileMove(LandmarkTile landmarkTile, int cost) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // Removing from the current landmark tiles after use before actually using -> bad move detection
        boolean landmarkTileWasPresent = state.removeLandmarkTile(landmarkTile);
        if (!landmarkTileWasPresent) {
            throw new IllegalStateException("Landmark tile was not present");
        }

        // Perform the common implementation of multistage moves
        ActionResult result = IMove.performMultiStageMove(ctx, state, landmarkTile.actions());

        // Place the fortress to the given region
        Player playerOnMove = state.getPlayerOnMove();
        landmarkTile.region().placeFortress(playerOnMove);

        // Pay the price for playing the card
        extractCoins(ctx, state, cost);

        return result;
    }

    @Override
    public int coinsToPlay() {
        return cost;
    }
}
