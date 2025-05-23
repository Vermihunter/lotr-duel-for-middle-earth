package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.AddSupportOfARaceAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(AddSupportOfARaceAction.class)
public class AddSupportOfARaceActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (AddSupportOfARaceAction) _move;

        return "Adds support of race " + move.raceToSupport().toString() + "\n";
    }
}
