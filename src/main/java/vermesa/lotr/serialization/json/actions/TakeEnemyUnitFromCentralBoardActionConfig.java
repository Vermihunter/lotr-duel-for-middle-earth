package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class TakeEnemyUnitFromCentralBoardActionConfig extends ActionConfig {
    public int UnitsToTake;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return null;
    }
}
