package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.PlaceUnitsOnCentralBoardAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceUnitsOnCentralBoardActionConfig extends ActionConfig {
    public List<String> UnitsToPlace;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        List<Region> regions = UnitsToPlace
                .stream()
                .map(regionMapper::get)
                .toList();

        return new PlaceUnitsOnCentralBoardAction(regions);
    }
}
