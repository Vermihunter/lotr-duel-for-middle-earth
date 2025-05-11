package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.Race;

public class AddSupportOfARaceAction implements IAction {
    private final Race raceToSupport;

    public AddSupportOfARaceAction(Race raceToSupport) {
        this.raceToSupport = raceToSupport;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
