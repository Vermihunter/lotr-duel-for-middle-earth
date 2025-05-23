package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.chapter_card_actions.PlayDiscardedChapterCardAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(PlayDiscardedChapterCardAction.class)
public class PlayDiscardedChapterCardActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction move) {
        return "Play any chapter card from the discard\n";
    }
}
