package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public record AddSupportOfARaceAction(Race raceToSupport) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        ActionResult result = ActionResult.OK;

        Player playerOnMove = state.getPlayerOnMove();
        var supportValues = playerOnMove.getSupportingRaces();
        // Save a copy to check after the action has been made
        var oldSupportValues = Arrays.copyOf(supportValues, supportValues.length);

        state.getPlayerOnMove().addSupportingRace(raceToSupport);

        // Check if both (there are two of the same race in the current version)
        // race cards have been collected by the same race
        if (supportValues[raceToSupport.ordinal()] == 2) {
            ArrayList<IMove> followUpMoves = new ArrayList<>();
            followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(new Race[]{raceToSupport}, 2, 1));
            result = new ActionResult(followUpMoves, false);
        }
        // Note that both the actions that fire the alliance token revealing cannot happen at the same time
        // because one is about collecting two of the same (meaning that it does not add a new race to support)
        // and the other one is about collecting support of three different races (meaning that it needs a new race
        // support to be added)
        else {
            ArrayList<Race> previouslySupportingRaces = getSupportingRaces(oldSupportValues);
            if (previouslySupportingRaces.size() == 2 && supportValues[raceToSupport.ordinal()] == 1) {
                ArrayList<IMove> followUpMoves = new ArrayList<>();

                previouslySupportingRaces.add(raceToSupport);
                followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(previouslySupportingRaces.toArray(new Race[3]), 1, 1));
                result = new ActionResult(followUpMoves, false);
            }

        }

        return result;
    }

    private ArrayList<Race> getSupportingRaces(int[] raceSupports) {
        return Arrays.stream(Race.values())
                .filter(race -> raceSupports[race.ordinal()] > 0)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
