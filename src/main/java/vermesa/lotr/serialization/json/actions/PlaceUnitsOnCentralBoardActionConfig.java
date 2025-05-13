package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.CentralBoardUnitPlacingStrategy;
import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion;
import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely;
import vermesa.lotr.model.actions.central_board_actions.PlaceUnitsOnCentralBoardAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;
import java.util.List;

public class PlaceUnitsOnCentralBoardActionConfig extends ActionConfig {
    public List<String> PossibleRegionsToPlace;
    public int UnitsToPlace;
    public CentralBoardUnitPlacingStrategy Strategy;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        List<Region> regions = PossibleRegionsToPlace
                .stream()
                .map(regionMapper::get)
                .toList();

        return (Strategy == CentralBoardUnitPlacingStrategy.ALL_TO_SAME_REGION)
                ? new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(regions, UnitsToPlace)
                : new ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely(regions, UnitsToPlace);
    }
}
