package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.coin_actions.GainExtraCoinsForDiscardedChapterCardAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class GainExtraCoinsForDiscardedChapterCardActionConfig extends ActionConfig {
    public int[] ExtraCoinsPerRound;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new GainExtraCoinsForDiscardedChapterCardAction(ExtraCoinsPerRound);
    }
}
