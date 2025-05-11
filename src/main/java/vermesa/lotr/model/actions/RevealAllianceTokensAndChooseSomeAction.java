package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.Race;

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
