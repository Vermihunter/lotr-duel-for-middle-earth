package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public class GainSkillAction implements IAction {
    private final SkillSet skillsToGain;

    public GainSkillAction(SkillSet skillsToGain) {
        this.skillsToGain = skillsToGain;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }

    public SkillSet getSkillsToGain() {
        return skillsToGain;
    }
}
