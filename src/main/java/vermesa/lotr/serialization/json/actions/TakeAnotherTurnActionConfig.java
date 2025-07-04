package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.basic_actions.TakeAnotherTurnAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class TakeAnotherTurnActionConfig extends ActionConfig {

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new TakeAnotherTurnAction();
    }
}
