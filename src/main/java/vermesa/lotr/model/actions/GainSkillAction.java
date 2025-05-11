package vermesa.lotr.model.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.Skill;
import vermesa.lotr.model.SkillSet;

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
