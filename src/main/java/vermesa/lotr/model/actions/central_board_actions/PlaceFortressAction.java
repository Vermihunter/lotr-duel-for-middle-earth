package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.player.Player;

public class PlaceFortressAction implements IAction {
    private final Region region;

    public PlaceFortressAction(Region region) {
        this.region = region;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        region.placeFortress(playerOnMove);

        return null;
    }
}
