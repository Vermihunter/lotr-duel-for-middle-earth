package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.List;

public class PlaceUnitsOnCentralBoardAction implements IAction {
    //private final
    private final List<Region> possibleUnitDestinations;
    private final int unitsToPlace;

    public PlaceUnitsOnCentralBoardAction(List<Region> possibleUnitDestinations, int unitsToPlace) {
        this.possibleUnitDestinations = possibleUnitDestinations;
        this.unitsToPlace = unitsToPlace;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {

        return null;
    }
}
