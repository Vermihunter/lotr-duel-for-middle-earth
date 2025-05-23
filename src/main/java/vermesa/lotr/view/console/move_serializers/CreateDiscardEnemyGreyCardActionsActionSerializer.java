package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.chapter_card_actions.CreateDiscardEnemyGreyCardActionsAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(CreateDiscardEnemyGreyCardActionsAction.class)
public class CreateDiscardEnemyGreyCardActionsActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        return "Choose an enemy grey chapter card to discard\n";
    }
}
