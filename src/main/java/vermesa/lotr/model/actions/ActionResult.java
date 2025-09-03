package vermesa.lotr.model.actions;

import vermesa.lotr.model.moves.IMove;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a result of {@link IAction}
 * - Note that it makes sense to separate these two fields the followUpActions array being
 * empty does not imply the shiftNextPlayer variable to be true e.g. the implementation of
 * the action {@link vermesa.lotr.model.actions.basic_actions.TakeAnotherTurnAction}.
 *
 * @param followUpActions Follow-up actions that must follow the given action
 * @param shiftNextPlayer Boolean value representing whether to shift the round to the next player or not
 */
public record ActionResult(List<List<IMove>> followUpActions, boolean shiftNextPlayer) implements Serializable {
    /**
     * Cached basic answer
     */
    public static final ActionResult OK = new ActionResult(List.of(), true);
}
