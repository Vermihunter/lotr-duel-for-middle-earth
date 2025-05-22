package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.ChapterCardPlayMove;
import vermesa.lotr.view.console.annotations.Serializes;
import vermesa.lotr.view.console.state_serializers.ChapterCardSerializer;

@Serializes(ChapterCardPlayMove.class)
public class PlayChapterCardMoveSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (ChapterCardPlayMove) _move;

        return "Play chapter card " + ChapterCardSerializer.serialize(move.chapterCard, 11);
    }
}
