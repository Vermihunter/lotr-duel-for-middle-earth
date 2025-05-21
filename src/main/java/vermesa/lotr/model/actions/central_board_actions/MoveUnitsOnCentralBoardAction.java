package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.player.Player;

import java.util.List;

public record MoveUnitsOnCentralBoardAction(List<CentralBoardUnitMovement> movements) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();

        for (CentralBoardUnitMovement movement : movements) {
            movement.from().removeUnits(movement.unitsToMove());
            movement.to().addUnits(playerOnMove, movement.unitsToMove());

        }

        return ActionResult.OK;
    }
}
