package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

import java.util.List;

public class MultiChoiceAction implements IAction {
    // The user may take any of the actions
    private final List<IAction> possibleActions;
    private final int actionsToTake;

    public MultiChoiceAction(List<IAction> possibleActions, int actionsToTake) {
        this.possibleActions = possibleActions;
        this.actionsToTake = actionsToTake;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {

        return null;
    }

}
