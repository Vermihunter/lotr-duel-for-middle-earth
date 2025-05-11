package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.central_board.Region;

public class PlaceFortressAction implements IAction {
    private final Region region;

    public PlaceFortressAction(Region region) {
        this.region = region;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
