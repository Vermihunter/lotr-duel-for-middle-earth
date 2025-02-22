package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.ActionResult;
import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.IAction;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.player.Player;

public abstract class LandmarkTile implements IAction {

    public abstract ActionResult action(GameContext ctx, GameState state);

    protected void placeFortressTo(RegionType region, Player player /*, Game game */) {

    }
}
