package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.TakeEnemyUnitFromCentralBoardAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(TakeEnemyUnitFromCentralBoardAction.class)
public class TakeEnemyUnitFromCentralBoardActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (TakeEnemyUnitFromCentralBoardAction) _move;
        var unitsToTakeSerialized = move.unitsToTake().stream()
                .map(unitsInRegion -> "Region: " + unitsInRegion.region().getRegionType().toString() + " - Units: " + unitsInRegion.units())
                .collect(Collectors.joining(",", "[", "]"));

        return "Take enemy units: " + unitsToTakeSerialized + "\n";
    }
}
