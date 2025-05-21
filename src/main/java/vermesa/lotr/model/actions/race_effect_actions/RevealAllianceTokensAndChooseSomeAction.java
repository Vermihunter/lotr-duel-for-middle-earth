package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;

import java.util.Arrays;
import java.util.List;


public record RevealAllianceTokensAndChooseSomeAction(Race[] racesToRevealFrom, int tokensToRevealPerRace,
                                                      int totalTokensToChoose) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        List<IMove> followUpMoves = Arrays.stream(racesToRevealFrom)
                .map(race -> (IMove) new ChooseAlianceTokensAction(race, tokensToRevealPerRace, totalTokensToChoose))
                .toList();

        return new ActionResult(List.of(followUpMoves), false);
    }
}
