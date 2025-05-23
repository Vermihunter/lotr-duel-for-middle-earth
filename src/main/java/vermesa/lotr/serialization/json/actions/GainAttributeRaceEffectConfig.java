package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.GainAttributeRaceEffect;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class GainAttributeRaceEffectConfig extends ActionConfig {
    public RaceEffectAttributes Attribute;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new GainAttributeRaceEffect(Attribute);
    }
}
