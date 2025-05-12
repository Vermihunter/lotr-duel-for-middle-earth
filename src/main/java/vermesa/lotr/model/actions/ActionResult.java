package vermesa.lotr.model.actions;

import java.util.List;

public record ActionResult(List<IAction> followUpActions, boolean shiftNextPlayer) {

    public static ActionResult OK = new ActionResult(List.of(), true);
}
