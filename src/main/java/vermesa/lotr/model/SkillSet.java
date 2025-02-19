package vermesa.lotr.model;

import java.util.Arrays;

public class SkillSet {
    private final int[] skillCounts;

    public SkillSet() {
        skillCounts = new int[Skill.values().length];
    }

    public SkillSet(int[] skillCounts) {
        this.skillCounts = skillCounts;
    }

    public SkillSet createAlternativeSkillset(Skill skill) {
        SkillSet skillSet = new SkillSet(Arrays.copyOf(skillCounts, skillCounts.length));
        skillSet.addSkill(skill, 1);
        return skillSet;
    }

    public void addSkill(Skill skill, int skillCount) {
        skillCounts[skill.ordinal()] += skillCount;
    }
}
