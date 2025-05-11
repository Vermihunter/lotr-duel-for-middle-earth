package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.SkillSet;

public class DiscardEnemyGreyCardAction implements IAction {
    private final SkillSet skillsetToDiscard;

    public DiscardEnemyGreyCardAction(SkillSet skillsetToDiscard) {
        this.skillsetToDiscard = skillsetToDiscard;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
