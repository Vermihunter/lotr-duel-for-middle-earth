package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;

import java.util.List;

public record TakeEnemyUnitFromCentralBoardAction(List<UnitsInRegion> unitsToTake) implements IMove {


    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        unitsToTake.forEach(unit -> unit.region().removeUnits(unit.units()));
        return ActionResult.OK;
    }
}
