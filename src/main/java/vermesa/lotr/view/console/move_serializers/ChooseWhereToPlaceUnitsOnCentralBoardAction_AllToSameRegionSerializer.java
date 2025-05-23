package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion.class)
public class ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion) _move;
        String regionsSerialized = move.possibleRegions().stream()
                .map(r -> r.getRegionType().toString())
                .collect(Collectors.joining(", ", "[", "]"));

        return "Place all the " + move.unitsToPlace() + " units in any of the following regions: " + regionsSerialized + "\n";
    }
}
