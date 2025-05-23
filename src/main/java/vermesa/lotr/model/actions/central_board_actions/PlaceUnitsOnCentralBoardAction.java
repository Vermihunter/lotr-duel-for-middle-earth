package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.List;

/**
 * Places units in given regions on the central board
 *
 * @param unitPlacings List of placings
 */
public record PlaceUnitsOnCentralBoardAction(List<UnitsInRegion> unitPlacings) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();

        unitPlacings.forEach(unitPlacing -> {
            unitPlacing.region().addUnits(playerOnMove, unitPlacing.units());
            playerOnMove.removeUnits(unitPlacing.units());
        });

        return ActionResult.OK;
    }

}
