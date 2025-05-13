package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.TakeEnemyUnitFromCentralBoardCollectingAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class TakeEnemyUnitFromCentralBoardActionConfig extends ActionConfig {
    public int UnitsToTake;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new TakeEnemyUnitFromCentralBoardCollectingAction(UnitsToTake);
    }
}
