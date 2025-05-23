package vermesa.lotr.model.actions.ring_quest_track_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

/**
 * Moves the player on the Quest of the ring track
 *
 * @param moveCount The number of moves to make
 */
public record MoveOnTheRingQuestTrackAction(int moveCount) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        ctx.getQuestOfTheRingTrack().movePlayer(playerOnMove, moveCount, ctx, state);

        return ActionResult.OK;
    }
}
