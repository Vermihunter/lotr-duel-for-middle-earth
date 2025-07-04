package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.ChapterCardDiscardMove;
import vermesa.lotr.view.console.annotations.Serializes;
import vermesa.lotr.view.console.state_serializers.ChapterCardSerializer;


@Serializes(ChapterCardDiscardMove.class)
public class DiscardChapterCardMoveSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (ChapterCardDiscardMove) _move;

        return "Discard chapter card " + ChapterCardSerializer.serialize(move.chapterCard, 11);
    }
}
