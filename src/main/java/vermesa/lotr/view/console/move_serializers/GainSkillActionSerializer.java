package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.chapter_card_actions.GainSkillAction;
import vermesa.lotr.view.console.annotations.Serializes;
import vermesa.lotr.view.console.state_serializers.SkillSetSerializer;

@Serializes(GainSkillAction.class)
public class GainSkillActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (GainSkillAction) _move;

        return "Gains skill " + SkillSetSerializer.serialize(move.skillsToGain(), 0) + "\n";
    }
}
