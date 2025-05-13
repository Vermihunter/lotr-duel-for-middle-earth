package vermesa.lotr.model.skills;

import java.util.stream.IntStream;

public class SkillSet {
    private final int[] skillCounts;

    /*public SkillSet() {
        skillCounts = new int[Skill.values().length];
    }*/

    public SkillSet(int[] skillCounts) {
        this.skillCounts = skillCounts;
    }

    public int missingSkills(SkillSet other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        if (this.skillCounts.length != other.skillCounts.length) {
            throw new IllegalArgumentException("SkillSets must have the same length");
        }

        return IntStream.range(0, skillCounts.length)
                .map(i -> Math.max(this.skillCounts[i] - other.skillCounts[i], 0))
                .sum();
    }

    public int[] getSkillCounts() {
        return skillCounts;
    }
    public boolean isOptional() {
        return false;
    }

    public void addSkill(Skill skill, int skillCount) {
        skillCounts[skill.ordinal()] += skillCount;
    }
}
