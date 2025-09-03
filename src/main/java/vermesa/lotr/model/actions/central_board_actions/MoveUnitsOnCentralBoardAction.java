package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.util.List;

/**
 * Moves a list of units from one region to another
 *
 * @param movements The movements to be made
 */
public record MoveUnitsOnCentralBoardAction(List<CentralBoardUnitMovement> movements) implements IMove, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();

        for (CentralBoardUnitMovement movement : movements) {
            movement.from().removeUnits(movement.unitsToMove());
            movement.to().addUnits(playerOnMove.getType(), movement.unitsToMove());
        }

        return ActionResult.OK;
    }
}
