package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseUnitsSourceToMoveAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(ChooseUnitsSourceToMoveAction.class)
public class ChooseUnitsSourceToMoveActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (ChooseUnitsSourceToMoveAction) _move;

        return "Move " + move.unitsToMove() + " units on the Central board\n";
    }
}
