package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.ring_quest_track_actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(MoveOnTheRingQuestTrackAction.class)
public class MoveOnTheRingQuestTrackActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (MoveOnTheRingQuestTrackAction) _move;

        return "Move " + move.moveCount() + " places on the ring quest track\n";
    }
}

