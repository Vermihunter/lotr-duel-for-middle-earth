package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.GainAttributeRaceEffect;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.HashMap;

@Serializes(GainAttributeRaceEffect.class)
public class GainAttributeRaceEffectSerializer implements IActionSerializer {
    private static final HashMap<RaceEffectAttributes, String> serializedAttributes = new HashMap<>();

    static {
        serializedAttributes.put(RaceEffectAttributes.BENEFIT_FROM_ANY_SKILL, "each round benefit from any skill");
        serializedAttributes.put(RaceEffectAttributes.PLACE_EXTRA_UNIT_FOR_RED_CARD, "place 1 additional unit when you play a red card");
        serializedAttributes.put(RaceEffectAttributes.IGNORE_LANDMARK_TILE_COST, "ignore the additional cost for playing landmark tiles");
        serializedAttributes.put(RaceEffectAttributes.PLACE_ANYWHERE_FOR_RED_CARD, "all units can be placed to any region when playing a red card");
    }

    @Override
    public String serialize(IAction _move) {
        var move = (GainAttributeRaceEffect) _move;

        return "Gain attribute - " + serializedAttributes.get(move.raceEffectAttribute()) + "\n";
    }
}

