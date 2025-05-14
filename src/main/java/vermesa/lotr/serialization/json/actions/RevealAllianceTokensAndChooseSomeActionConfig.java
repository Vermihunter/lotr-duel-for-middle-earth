package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.race_effect_actions.RevealAllianceTokensAndChooseSomeAction;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class RevealAllianceTokensAndChooseSomeActionConfig extends ActionConfig {
    public int TokensToReveal;
    public int TokensToChoose;
    public Race[] RacesToRevealFrom;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new RevealAllianceTokensAndChooseSomeAction(RacesToRevealFrom, TokensToReveal, TokensToChoose);
    }
}
