package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.basic_actions.MultiChoiceAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.utils.Combinations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Chooses unitsToMove units (=Regions) to move units from
 *
 * @param moveStrategy
 * @param unitsToMove
 */
public record ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy moveStrategy,
                                            int unitsToMove) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        var regions = ctx.getCentralBoard().regions();
        List<Region> regionsToMove = new ArrayList<>();
        int[] regionRepetitions = new int[regions.size()];

        IntStream.range(0, regions.size())
                .filter(i -> regions.get(i).getUnit() == playerOnMove.getType())
                .forEach(region -> {
                    regionRepetitions[regionsToMove.size()] = regions.get(region).getUnitCount();
                    regionsToMove.add(regions.get(region));
                });


        ArrayList<IMove> followUpMoves = new ArrayList<>();

        int[] finalRegionRepetitions = Arrays.stream(regionRepetitions).limit(regionsToMove.size()).toArray();
        for (var regionGroup : Combinations.combinationsWithRepeats(regionsToMove, finalRegionRepetitions, unitsToMove)) {
            followUpMoves.add(new ChooseUnitsDestinationToMoveAction(regionGroup));
        }

        if (followUpMoves.isEmpty()) {
            return ActionResult.OK;
        }

        return new ActionResult(List.of(followUpMoves), false);
    }
}
