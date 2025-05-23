package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.chapter_card_actions.DiscardEnemyGreyCardAction;
import vermesa.lotr.view.console.annotations.Serializes;
import vermesa.lotr.view.console.state_serializers.SkillSetSerializer;

@Serializes(DiscardEnemyGreyCardAction.class)
public class DiscardEnemyGreyCardActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (DiscardEnemyGreyCardAction) _move;

        return "Discard enemy grey card that gained skills: " + SkillSetSerializer.serialize(move.skillSetToDiscard(), 0);
    }
}
