package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseEnemyFortressToRemoveAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class RemoveEnemyFortressActionConfig extends ActionConfig {


    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new ChooseEnemyFortressToRemoveAction();
    }
}
