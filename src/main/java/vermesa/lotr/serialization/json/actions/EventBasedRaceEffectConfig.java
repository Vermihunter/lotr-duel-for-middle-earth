package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.EventBasedRaceEffect;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class EventBasedRaceEffectConfig extends ActionConfig {
    public RaceEffectCallbackEventType Trigger;
    public ActionConfig Effect;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new EventBasedRaceEffect(Trigger, Effect.constructAction(regionMapper));
    }
}
