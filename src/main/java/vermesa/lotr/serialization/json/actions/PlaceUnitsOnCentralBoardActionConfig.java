package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.PlaceUnitsOnCentralBoardAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;
import java.util.List;

public class PlaceUnitsOnCentralBoardActionConfig extends ActionConfig {
    public List<String> PossibleRegionsToPlace;
    public int UnitsToPlace;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        // TODO: replace dummmy implementation

        List<Region> regions = PossibleRegionsToPlace
                .stream()
                .map(regionMapper::get)
                .toList();

        return new PlaceUnitsOnCentralBoardAction(regions, UnitsToPlace);
    }
}
