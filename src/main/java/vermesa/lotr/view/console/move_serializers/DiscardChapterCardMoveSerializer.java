package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.ChapterCardDiscardMove;
import vermesa.lotr.view.console.annotations.Serializes;


@Serializes(ChapterCardDiscardMove.class)
public class DiscardChapterCardMoveSerializer implements IActionSerializer {

    @Override
    public String serialize(IAction _move) {
        var move = (ChapterCardDiscardMove) _move;
        var chapterCard = move.chaptercard;

        StringBuilder builder = new StringBuilder();


        return "Discard chapter card " + ChapterCardSerializer.serializeChapterCard(move.chaptercard, 13);
    }
}
