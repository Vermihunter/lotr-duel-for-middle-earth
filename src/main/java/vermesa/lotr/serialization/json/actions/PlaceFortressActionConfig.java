package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.PlaceFortressAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class PlaceFortressActionConfig extends ActionConfig {
    public Region region;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new PlaceFortressAction(region);
    }
}
