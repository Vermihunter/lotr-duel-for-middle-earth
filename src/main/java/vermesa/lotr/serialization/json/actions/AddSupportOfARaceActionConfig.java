package vermesa.lotr.serialization.json.actions;


import vermesa.lotr.model.Race;
import vermesa.lotr.model.actions.AddSupportOfARaceAction;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class AddSupportOfARaceActionConfig extends ActionConfig {
    public Race RaceToSupport;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new AddSupportOfARaceAction(RaceToSupport);
    }
}
