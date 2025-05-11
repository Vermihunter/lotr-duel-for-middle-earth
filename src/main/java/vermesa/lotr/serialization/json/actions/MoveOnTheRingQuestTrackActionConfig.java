package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class MoveOnTheRingQuestTrackActionConfig extends ActionConfig {
    public int MoveCount;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new MoveOnTheRingQuestTrackAction(MoveCount);
    }
}
