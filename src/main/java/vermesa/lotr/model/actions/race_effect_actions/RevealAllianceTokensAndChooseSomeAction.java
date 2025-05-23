package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;

import java.util.Arrays;
import java.util.List;


/**
 * Reveals some alliance tokens to both players and uses others
 *
 * @param tokensToReveal Alliance tokens that are revealed to both players
 * @param tokensToChoose Alliance tokens that's effect is used on the player on move
 */
public record RevealAllianceTokensAndChooseSomeAction(AllianceToken[] tokensToReveal,
                                                      AllianceToken[] tokensToChoose) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        /*
        for(AllianceToken token : tokensToReveal) {
            // reveal
        }
        */

        for (AllianceToken token : tokensToChoose) {
            token.action().action(ctx, state);
            state.removeAllianceTokens(token);
        }

        return ActionResult.OK;
    }
}
