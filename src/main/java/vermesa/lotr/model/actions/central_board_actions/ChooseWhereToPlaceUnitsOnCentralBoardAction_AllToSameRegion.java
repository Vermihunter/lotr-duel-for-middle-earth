package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public record ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List<Region> possibleRegions,
                                                                          int unitsToPlace) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        boolean hasPlaceAnywhereAttribute = playerOnMove.getPlayerState().hasAttribute(RaceEffectAttributes.PLACE_ANYWHERE_FOR_RED_CARD);

        var regions = hasPlaceAnywhereAttribute
                ? ctx.getCentralBoard().regions()
                : possibleRegions;

        boolean hasPlaceExtraUnitAttribute = playerOnMove.getPlayerState().hasAttribute(RaceEffectAttributes.PLACE_EXTRA_UNIT_FOR_RED_CARD);
        int extraUnitsToPlace = hasPlaceExtraUnitAttribute ? 1 : 0;

        var possibleFollowUps = regions.stream()
                .map(region -> regionToAction(region, unitsToPlace + extraUnitsToPlace))
                .toList();

        return new ActionResult(possibleFollowUps, false);
    }

    private IMove regionToAction(Region region, int unitCount) {
        return new PlaceUnitsOnCentralBoardAction(new ArrayList<>() {{
            add(new PlaceUnitOnCentralBoardContext(region, unitCount));
        }});
    }
}
