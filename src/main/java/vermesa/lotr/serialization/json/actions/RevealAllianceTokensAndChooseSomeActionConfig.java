package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.Race;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class RevealAllianceTokensAndChooseSomeActionConfig extends ActionConfig {
    public int TokensToReveal;
    public Race[] RacesToRevealFrom;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return null;
    }
}
