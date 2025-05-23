package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adds a support of a concrete race to the player who is currently on move.
 * Checks if any of the two conditions met that gain a new alliance token for the player:
 * <ul>
 *     <li>The player has gained the second support of the same race </li>
 *     <li>The player has gained the support of three different races (for the first time)</li>
 * </ul>
 * If these conditions are met, {@link RevealAllianceTokensAndChooseSomeAction} move(s) are added as
 * follow-up.
 *
 * @param raceToSupport The race that the player has gained the support of
 */
public record AddSupportOfARaceAction(Race raceToSupport) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        ActionResult result = ActionResult.OK;

        Player playerOnMove = state.getPlayerOnMove();
        var supportValues = playerOnMove.getSupportingRaces();
        // Save a copy to check after the action has been made
        var oldSupportValues = Arrays.copyOf(supportValues, supportValues.length);

        state.getPlayerOnMove().addSupportingRace(raceToSupport);

        var availableAllianceTokens = state.getAllianceTokens();

        // Check if both (there are two of the same race in the current version)
        // race cards have been collected by the same race
        if (supportValues[raceToSupport.ordinal()] == 2) {
            ArrayList<IMove> followUpMoves = new ArrayList<>();

            var raceAllianceTokens = availableAllianceTokens.get(raceToSupport);
            var revealedAllianceTokens = new AllianceToken[]{raceAllianceTokens.get(0), raceAllianceTokens.get(1)};
            if (revealedAllianceTokens[0] == null || revealedAllianceTokens[1] == null) {
                throw new IllegalArgumentException("");
            }

            followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(revealedAllianceTokens, new AllianceToken[]{raceAllianceTokens.get(0)}));
            followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(revealedAllianceTokens, new AllianceToken[]{raceAllianceTokens.get(1)}));

            result = new ActionResult(List.of(followUpMoves), false);
        }
        // Note that both the actions that fire the alliance token revealing cannot happen at the same time
        // because one is about collecting two of the same (meaning that it does not add a new race to support)
        // and the other one is about collecting support of three different races (meaning that it needs a new race
        // support to be added)
        else {
            ArrayList<Race> previouslySupportingRaces = getSupportingRaces(oldSupportValues);
            if (previouslySupportingRaces.size() == 2 && supportValues[raceToSupport.ordinal()] == 1) {
                ArrayList<IMove> followUpMoves = new ArrayList<>();

                Race race1 = previouslySupportingRaces.get(0);
                Race race2 = previouslySupportingRaces.get(1);
                Race race3 = raceToSupport;

                AllianceToken at1 = availableAllianceTokens.get(race1).getFirst();
                AllianceToken at2 = availableAllianceTokens.get(race2).getFirst();
                AllianceToken at3 = availableAllianceTokens.get(race3).getFirst();

                if (at1 == null || at2 == null || at3 == null) {
                    throw new IllegalArgumentException("Races must have at least one alliance token");
                }

                var revealedAllianceTokens = new AllianceToken[]{at1, at2, at3};
                followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(revealedAllianceTokens, new AllianceToken[]{at1}));
                followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(revealedAllianceTokens, new AllianceToken[]{at2}));
                followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(revealedAllianceTokens, new AllianceToken[]{at3}));

                result = new ActionResult(List.of(followUpMoves), false);
            }

        }

        return result;
    }

    /**
     * Helper function that returns the races that are supporting a given player
     *
     * @param raceSupports The array representing how many supporters of each race the player has
     * @return The list of races that support the player according ot the raceSupports array
     */
    private ArrayList<Race> getSupportingRaces(int[] raceSupports) {
        return Arrays.stream(Race.values())
                .filter(race -> raceSupports[race.ordinal()] > 0)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
