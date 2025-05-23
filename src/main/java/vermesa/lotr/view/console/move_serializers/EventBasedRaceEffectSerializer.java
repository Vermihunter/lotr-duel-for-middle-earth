package vermesa.lotr.view.console.move_serializers;


import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.EventBasedRaceEffect;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventType;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.HashMap;

@Serializes(EventBasedRaceEffect.class)
public class EventBasedRaceEffectSerializer implements IActionSerializer {
    private static HashMap<RaceEffectCallbackEventType, String> callbackEventTypesSerialized = new HashMap<>();

    static {
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.CHAPTER_CARD_YELLOW_USED, "the player plays a yellow chapter card");
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.CHAPTER_CARD_GREEN_USED, "the player plays a green chapter card");
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.CHAPTER_CARD_BLUE_USED, "the player plays a blue chapter card");
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.CHAPTER_CARD_DISCARDED, "the player discards a chapter card");
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.LANDMARK_TILE_USED, "the player plays a landmark tile");
        callbackEventTypesSerialized.put(RaceEffectCallbackEventType.CHAINING_SYMBOL_USED, "the player plays a chapter card using a chaining symbol");
    }

    @Override
    public String serialize(IAction _move) {
        var move = (EventBasedRaceEffect) _move;
        var action = move.action();

        return "Gains an extra power when " + callbackEventTypesSerialized.get(move.callbackEventType()) + ": " + ActionSerializerRegistry.serialize(action) + "\n";
    }
}
