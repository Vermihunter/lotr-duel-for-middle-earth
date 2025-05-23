package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely.class)
public class ChooseWhereToPlaceUnitsActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely) _move;
        ;

        return "Place " + move.unitsToPlace() + " units on central board\n";
    }
}

