package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.util.List;

/**
 * A move that creates follow-up moves to choose concrete fortresses to remove
 */
public class ChooseEnemyFortressToRemoveAction implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player enemyPlayer = state.getNextPlayerOnMove();

        // Collect the actions
        var followUpActions = ctx.getCentralBoard().regions().stream()
                .filter(region -> region.getFortress() == enemyPlayer.getType()) // Filter regions that have a fortress of the enemy player
                .map(region -> (IMove) new RemoveEnemyFortressAction(region, enemyPlayer)) // Create a move for each of those regions
                .toList();

        if (followUpActions.isEmpty()) {
            return new ActionResult(List.of(), true);
        }

        return new ActionResult(List.of(followUpActions), false);
    }
}
