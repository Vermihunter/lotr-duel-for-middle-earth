package vermesa.lotr.serialization.json.actions;


import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.race_effect_actions.AddSupportOfARaceAction;
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
