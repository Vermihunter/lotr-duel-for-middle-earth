package vermesa.lotr.model.actions;

import vermesa.lotr.model.moves.IMove;

import java.util.List;

public record ActionResult(List<IMove> followUpActions, boolean shiftNextPlayer) {

    public static final ActionResult OK = new ActionResult(List.of(), true);
}
