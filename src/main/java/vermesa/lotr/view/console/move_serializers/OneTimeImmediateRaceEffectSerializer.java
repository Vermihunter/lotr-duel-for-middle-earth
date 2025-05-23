package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.OneTimeImmediateRaceEffect;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(OneTimeImmediateRaceEffect.class)
public class OneTimeImmediateRaceEffectSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (OneTimeImmediateRaceEffect) _move;

        return ActionSerializerRegistry.serialize(move.action());
    }
}
