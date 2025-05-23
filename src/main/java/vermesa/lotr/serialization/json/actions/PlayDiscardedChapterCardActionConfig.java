package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.chapter_card_actions.PlayDiscardedChapterCardAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class PlayDiscardedChapterCardActionConfig extends ActionConfig {
    public int CardsToPlay;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {

        return new PlayDiscardedChapterCardAction();
    }
}
