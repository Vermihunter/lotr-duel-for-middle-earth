package vermesa.lotr.serialization.json.actions;


import vermesa.lotr.model.actions.central_board_actions.CentralBoardUnitMoveStrategy;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseUnitsSourceToMoveAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class MoveUnitsOnCentralBoardActionConfig extends ActionConfig {
    public CentralBoardUnitMoveStrategy Strategy;
    public int UnitsToMove;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new ChooseUnitsSourceToMoveAction(Strategy, UnitsToMove);
    }
}
