package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseEnemyFortressToRemoveAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(ChooseEnemyFortressToRemoveAction.class)
public class ChooseEnemyFortressToRemoveActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction move) {
        return "Remove an enemy fortress from any region\n";
    }
}
