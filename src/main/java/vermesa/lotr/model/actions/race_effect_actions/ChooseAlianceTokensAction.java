package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.race_effects.Race;

public record ChooseAlianceTokensAction(Race raceToChooseFrom, int tokensToReveal,
                                        int tokensToChoose) implements IMove {
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
