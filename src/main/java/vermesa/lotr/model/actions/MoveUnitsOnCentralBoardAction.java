package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public class MoveUnitsOnCentralBoardAction implements IAction {
    private final CentralBoardUnitMoveStrategy[] possibleMoves;

    public MoveUnitsOnCentralBoardAction(CentralBoardUnitMoveStrategy[] possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
