package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.RemoveEnemyFortressAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(RemoveEnemyFortressAction.class)
public class RemoveEnemyFortressActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (RemoveEnemyFortressAction) _move;

        return "Remove enemy fortress from " + move.regionToRemoveFrom().toString() + "\n";
    }
}
