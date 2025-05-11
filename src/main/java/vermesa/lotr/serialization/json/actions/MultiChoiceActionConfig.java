package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.MultiChoiceAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;
import java.util.List;

public class MultiChoiceActionConfig extends ActionConfig {
    // The user may take any of the actions
    public List<ActionConfig> PossibleActions;
    public int ActionsToTake;

    /*
    public MultiChoiceActionConfig(List<ActionConfig> possibleActions, int actionsToTake) {
        PossibleActions = possibleActions;
        ActionsToTake = actionsToTake;
    }

     */

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        //return new MultiChoiceAction(PossibleActions,  ActionsToTake);
        return null;
    }
}
