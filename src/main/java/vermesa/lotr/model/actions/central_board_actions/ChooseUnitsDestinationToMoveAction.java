package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.utils.Combinations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Adds follow-up moves to choose destination for moving units from {@link #fromRegions()}
 * @param fromRegions The list of regions to move from (1 unit each)
 */
public record ChooseUnitsDestinationToMoveAction(List<Region> fromRegions) implements IMove {
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        List<List<Region>> regionNeighboringRegions = fromRegions.stream()
                .map(Region::getConnectedRegions)
                .toList();

        ArrayList<IMove> followUpMoves = new ArrayList<>();

        var allDestinationRegionOptions = Combinations.cartesianProduct(regionNeighboringRegions);
        for (var destinationOptionsForRegion : allDestinationRegionOptions) {

            List<CentralBoardUnitMovement> movements = new ArrayList<>();
            for (int i = 0; i < destinationOptionsForRegion.size(); i++) {
                movements.add(new CentralBoardUnitMovement(
                        fromRegions.get(i), destinationOptionsForRegion.get(i), 1));
            }

            followUpMoves.add(new MoveUnitsOnCentralBoardAction(movements));
        }

        if (followUpMoves.isEmpty()) {
            return ActionResult.OK;
        }

        return new ActionResult(List.of(followUpMoves), false);
    }
}
