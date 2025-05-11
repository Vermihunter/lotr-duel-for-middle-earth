package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;

public class MoveOnTheRingQuestTrackAction implements IAction {
    private final int moveCount;

    public MoveOnTheRingQuestTrackAction(int moveCount) {
        this.moveCount = moveCount;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
