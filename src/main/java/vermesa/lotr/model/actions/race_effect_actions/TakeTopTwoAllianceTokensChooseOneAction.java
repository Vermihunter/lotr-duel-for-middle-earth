package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Grey Havens landmark effect that lets the player choose
 * any race, choose the top two alliance tokens from them and play one of them.
 *
 * @param RacesToRevealFrom Races that the player can choose from
 * @param TokensToReveal    The number of alliance tokens that will be revealed
 * @param TokensToChoose    The number of tokens to choose from
 */
public record TakeTopTwoAllianceTokensChooseOneAction(Race[] RacesToRevealFrom, int TokensToReveal,
                                                      int TokensToChoose) implements IMove {
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        List<IMove> followUpMoves = new ArrayList<>();

        var allianceTokens = state.getAllianceTokens();
        for (Race race : RacesToRevealFrom) {
            AllianceToken[] tokensToReveal = allianceTokens.get(race).stream()
                    .limit(TokensToReveal)
                    .toArray(AllianceToken[]::new);

            AllianceToken[] tokensToChoose = allianceTokens.get(race).stream()
                    .limit(TokensToChoose)
                    .toArray(AllianceToken[]::new);


            followUpMoves.add(new RevealAllianceTokensAndChooseSomeAction(tokensToReveal, tokensToChoose));
        }

        return new ActionResult(List.of(followUpMoves), false);
    }
}
