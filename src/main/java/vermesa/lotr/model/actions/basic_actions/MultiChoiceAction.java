package vermesa.lotr.model.actions.basic_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @param possibleActions
 * @param actionsToTake
 */
public record MultiChoiceAction(List<? extends IAction> possibleActions, int actionsToTake) implements IMove {


    public static <T> List<ArrayList<T>> combinations(List<T> list, int k) {
        List<ArrayList<T>> result = new ArrayList<>();
        backtrack(list, k, 0, new ArrayList<>(), result);
        return result;
    }

    private static <T> void backtrack(
            List<T> list,
            int k,
            int start,
            List<T> current,
            List<ArrayList<T>> result
    ) {
        if (current.size() == k) {
            // copy the current combination
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            current.add(list.get(i));
            backtrack(list, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        var allMoveSets = allMoveSets();

        if (allMoveSets.isEmpty()) {
            return ActionResult.OK;
        }

        return new ActionResult(List.of(allMoveSets), false);
    }

    public ArrayList<IMove> allMoveSets() {
        // Collects all combinations
        List<ArrayList<IAction>> combos = combinations(
                new ArrayList<>(possibleActions),
                actionsToTake);

        return combos.stream()
                .map(TakeAllActions::new)
                .collect(Collectors.toCollection(ArrayList::new));

    }


}
