package vermesa.lotr.model;

import java.util.Arrays;

public class SkillSet {
    private final int[] skillCounts;
    private final boolean isOptional;

    public SkillSet() {
        skillCounts = new int[Skill.values().length];
        isOptional = false;
    }

    public SkillSet(int[] skillCounts, boolean isOptional) {
        this.skillCounts = skillCounts;
        this.isOptional = isOptional;
    }

    /*
    public SkillSet createAlternativeSkillset(Skill skill) {
        SkillSet skillSet = new SkillSet(Arrays.copyOf(skillCounts, skillCounts.length), isOptional);
        skillSet.addSkill(skill, 1);
        return skillSet;
    }
    */

    public void addSkill(Skill skill, int skillCount) {
        skillCounts[skill.ordinal()] += skillCount;
    }
}
