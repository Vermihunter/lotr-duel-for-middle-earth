package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.basic_actions.TakeAnotherTurnAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(TakeAnotherTurnAction.class)
public class TakeAnotherTurnActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction move) {
        return "Take another turn after finishing this one\n";
    }
}
