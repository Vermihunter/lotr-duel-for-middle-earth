package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.TakeEnemyUnitFromCentralBoardCollectingAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(TakeEnemyUnitFromCentralBoardCollectingAction.class)
public class TakeEnemyUnitFromCentralBoardCollectingActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (TakeEnemyUnitFromCentralBoardCollectingAction) _move;

        return "Take " + move.unitsToTake() + " units from the enemy from any region\n";
    }
}
