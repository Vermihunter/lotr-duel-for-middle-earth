package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public class RevealAllianceTokensAndChooseSomeAction implements IAction {
    private final int tokensToReveal;
    private final Race[] racesToRevealFrom;

    public RevealAllianceTokensAndChooseSomeAction(int tokensToReveal, Race[] racesToRevealFrom) {
        this.tokensToReveal = tokensToReveal;
        this.racesToRevealFrom = racesToRevealFrom;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
