package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseUnitsDestinationToMoveAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(ChooseUnitsDestinationToMoveAction.class)
public class ChooseUnitsDestinationToMoveActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (ChooseUnitsDestinationToMoveAction) _move;

        String regionsArray = move.fromRegions().stream()
                .map(r -> r.getRegionType().toString())
                .collect(Collectors.joining(",", "[", "]"));

        return "Move units from regions: " + regionsArray + "\n";
    }
}
