package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.central_board.Region;

import java.util.List;

public class PlaceUnitsOnCentralBoardAction implements IAction {
    //private final
    private final List<Region> possibleUnitDestinations;

    public PlaceUnitsOnCentralBoardAction(List<Region> possibleUnitDestinations) {
        this.possibleUnitDestinations = possibleUnitDestinations;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
