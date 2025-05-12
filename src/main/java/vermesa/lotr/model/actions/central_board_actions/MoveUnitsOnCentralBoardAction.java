package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

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
