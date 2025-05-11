package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.serialization.json.SkillsetConfig;

import java.util.HashMap;

public class GainSkillActionConfig extends ActionConfig {
    public SkillsetConfig SkillSet;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return null;
    }
}
