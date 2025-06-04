package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.moves.IMove;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely(List<Region> possibleRegions,
                                                                          int unitsToPlace) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // Currently there is a maximum of 2 in the game and the current easy implementation would not work for higher numbers
        if (unitsToPlace > 2) {
            throw new IllegalArgumentException("unitsToPlace > 2");
        }

        List<IMove> actions = new ArrayList<>();
        for (int i = 0; i < possibleRegions.size(); i++) {
            int finalI = i;
            actions.add(new PlaceUnitsOnCentralBoardAction(new ArrayList<>() {{
                add(new UnitsInRegion(possibleRegions.get(finalI), 2));
            }}));

            for (int j = i + 1; j < possibleRegions.size(); j++) {
                int finalJ = j;
                actions.add(new PlaceUnitsOnCentralBoardAction(new ArrayList<>() {{
                    add(new UnitsInRegion(possibleRegions.get(finalI), 1));
                    add(new UnitsInRegion(possibleRegions.get(finalJ), 1));
                }}));
            }
        }

        return new ActionResult(List.of(actions), false);
    }
}
