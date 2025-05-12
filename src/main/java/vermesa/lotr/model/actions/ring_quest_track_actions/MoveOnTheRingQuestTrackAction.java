package vermesa.lotr.model.actions.ring_quest_track_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public class MoveOnTheRingQuestTrackAction implements IAction {
    private final int moveCount;

    public MoveOnTheRingQuestTrackAction(int moveCount) {
        this.moveCount = moveCount;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        //ctx.

        return null;
    }
}
