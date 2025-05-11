package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class GetCoinsFromReserveActionConfig extends ActionConfig {
    public int CoinsToGet;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new GetCoinsFromReserveAction(CoinsToGet);
    }
}
