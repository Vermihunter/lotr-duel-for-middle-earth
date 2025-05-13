package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;

import java.util.ArrayList;
import java.util.List;

public record ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List<Region> possibleRegions,
                                                                          int unitsToPlace) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        var possibleFollowUps = possibleRegions.stream()
                .map(this::regionToAction)
                .toList();

        return new ActionResult(possibleFollowUps, false);
    }

    private IMove regionToAction(Region region) {
        return new PlaceUnitsOnCentralBoardAction(new ArrayList<>() {{
            add(new PlaceUnitOnCentralBoardContext(region, unitsToPlace));
        }});
    }
}
