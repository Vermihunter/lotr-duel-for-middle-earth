package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.*;

// Constructs the TakeEnemyUnitFromCentralBoardAction moves that a player can take
public record TakeEnemyUnitFromCentralBoardCollectingAction(int unitsToTake) implements IAction {


    /**
     * Returns all bounded compositions of `total` into regions.size() parts,
     * but emits each combo as a list of EnemyUnitsInRegion with units>0.
     */
    private static List<ArrayList<UnitsInRegion>> generateAllocations(List<UnitsInRegion> regions, int total) {
        List<ArrayList<UnitsInRegion>> result = new ArrayList<>();
        int M = regions.size();
        int[] current = new int[M];
        dfs(regions, 0, total, current, result);
        return result;
    }

    // recursively build all bounded compositions of `total` into `regions.size()` parts

    private static void dfs(List<UnitsInRegion> regions,
                            int idx, int remaining, int[] current, List<ArrayList<UnitsInRegion>> result) {
        if (idx == regions.size()) {
            if (remaining == 0) {
                // build a list of only those regions where we take >0 units
                ArrayList<UnitsInRegion> alloc = new ArrayList<>();
                for (int i = 0; i < current.length; i++) {
                    int taken = current[i];
                    if (taken > 0) {
                        var original = regions.get(i);
                        alloc.add(new UnitsInRegion(original.region(), taken));
                    }
                }
                result.add(alloc);
            }
            return;
        }

        // can take from 0 up to min(available, remaining)
        int maxCanTake = Math.min(regions.get(idx).units(), remaining);
        for (int t = 0; t <= maxCanTake; t++) {
            current[idx] = t;
            dfs(regions, idx + 1, remaining - t, current, result);
        }
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player enemy = state.getNextPlayerOnMove();
        var regions = ctx.getCentralBoard().regions();

        // Construct helper for possible regions
        var enemyUnitsInRegions = regions.stream()
                .filter(region -> region.getUnit() == enemy)
                .map(region -> new UnitsInRegion(region, region.getUnitCount()))
                .toList();

        // Collect all possible allocations
        var allocations = generateAllocations(enemyUnitsInRegions, unitsToTake);

        // Map each allocation to a concrete TakeEnemyUnitFromCentralBoardAction
        List<IMove> actions = allocations.stream()
                .map(alloc -> (IMove) new TakeEnemyUnitFromCentralBoardAction(alloc))
                .toList();

        return new ActionResult(List.of(actions), false);
    }
}
