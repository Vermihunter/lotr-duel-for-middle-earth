package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;

public interface IActionSerializer {
    String serialize(IAction move);
}
