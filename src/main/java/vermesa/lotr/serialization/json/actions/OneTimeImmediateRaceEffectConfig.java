package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.OneTimeImmediateRaceEffect;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class OneTimeImmediateRaceEffectConfig extends ActionConfig {
    public ActionConfig Effect;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {

        return new OneTimeImmediateRaceEffect(Effect.constructAction(regionMapper));
    }
}
