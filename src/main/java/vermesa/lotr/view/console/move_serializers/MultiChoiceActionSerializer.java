package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.basic_actions.MultiChoiceAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.stream.Collectors;

@Serializes(MultiChoiceAction.class)
public class MultiChoiceActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (MultiChoiceAction) _move;
        String actionsSerialized = move.possibleActions().stream()
                .map(a -> ActionSerializerRegistry.getAll().get(a.getClass()).serialize(a))
                .collect(Collectors.joining("\n\t\t\t- "));

        return "Choose " + move.actionsToTake() + " actions from the following moves:\n" + actionsSerialized;
    }
}
