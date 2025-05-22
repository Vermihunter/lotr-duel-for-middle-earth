package vermesa.lotr.model.skills;

import java.util.List;
import java.util.stream.IntStream;

public class SkillSet {
    private final int[] skillCounts;

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

    public static SkillSet combine(List<? extends SkillSet> skillsets) {
        var resultSkillCounts = new int[skillsets.size()];

        for (var skillset : skillsets) {
            var skillCounts = skillset.skillCounts;
            for (int i = 0; i < skillCounts.length; i++) {
                resultSkillCounts[i] += skillCounts[i];
            }
        }

        return new SkillSet(resultSkillCounts);
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
