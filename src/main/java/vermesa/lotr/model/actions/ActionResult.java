package vermesa.lotr.model.actions;

import vermesa.lotr.model.moves.IMove;

import java.util.List;

/**
 * Represents a result of {@link IAction}
 *
 * @param followUpActions Follow-up actions that must follow the given action
 * @param shiftNextPlayer Boolean value representing whether to shift the round to the next player or not
 */
public record ActionResult(List<List<IMove>> followUpActions, boolean shiftNextPlayer) {
    /**
     * Cached basic answer
     */
    public static final ActionResult OK = new ActionResult(List.of(), true);
}
