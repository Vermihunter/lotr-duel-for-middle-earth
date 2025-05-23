package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.basic_actions.TakeAllActions;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(TakeAllActions.class)
public class TakeAllActionsSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (TakeAllActions) _move;
        return move.actions().stream()
                .map(a -> ActionSerializerRegistry.getAll().get(a.getClass()).serialize(a))
                .collect(Collectors.joining("\n\t\t\t- "));
    }
}

