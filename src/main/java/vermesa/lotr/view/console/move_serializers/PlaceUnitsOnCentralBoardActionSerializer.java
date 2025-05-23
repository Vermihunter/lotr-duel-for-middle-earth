package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.PlaceUnitsOnCentralBoardAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(PlaceUnitsOnCentralBoardAction.class)
public class PlaceUnitsOnCentralBoardActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (PlaceUnitsOnCentralBoardAction) _move;
        String placingsSerialized = move.unitPlacings().stream()
                .map(placing -> "Region: " + placing.region().getRegionType() + " - Units: " + placing.units())
                .collect(Collectors.joining(",\n\t\t\t- "));

        return "Place units on central board: \n\t\t\t- " + placingsSerialized + "\n";
    }
}
