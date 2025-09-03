package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;

/**
 * Removes a fortress from a given region and returns it to the player it belongs to
 *
 * @param regionToRemoveFrom The region where the fortress is removed from
 */
public record RemoveEnemyFortressAction(Region regionToRemoveFrom,
                                        Player playerToGiveFortressBackTo) implements IMove, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {

        regionToRemoveFrom.removeFortress();
        playerToGiveFortressBackTo.placeBackFortress();

        return ActionResult.OK;
    }
}
