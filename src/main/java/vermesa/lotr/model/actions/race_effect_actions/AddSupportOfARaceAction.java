package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public class AddSupportOfARaceAction implements IAction {
    private final Race raceToSupport;

    public AddSupportOfARaceAction(Race raceToSupport) {
        this.raceToSupport = raceToSupport;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // TODO: check if any of the two principles have been fulfilled by the support of this race
        state.getPlayerOnMove().addSupportingRace(raceToSupport);

        return ActionResult.OK;
    }
}
