package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.MoveUnitsOnCentralBoardAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(MoveUnitsOnCentralBoardAction.class)
public class MoveUnitsOnCentralBoardActionSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (MoveUnitsOnCentralBoardAction) _move;

        String movementsArray = move.movements().stream()
                .map(movement -> "[ From: " + movement.from().getRegionType() + " - To: " + movement.to().getRegionType() + " ]")
                .collect(Collectors.joining(",", "[", "]"));

        return "Move units on central board: " + movementsArray + "\n";
    }
}
