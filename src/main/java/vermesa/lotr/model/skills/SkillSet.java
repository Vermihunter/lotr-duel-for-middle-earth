package vermesa.lotr.model.skills;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Base implementation of a SkillSet that represents the fixed skill sets in {@link ExtendedSkillSet}
 */
public class SkillSet {
    /**
     * Represents an array holding the number of skills for each type of {@link Skill}
     */
    private final int[] skillCounts;

    public SkillSet(int[] skillCounts) {
        this.skillCounts = skillCounts;
    }

    /**
     * Combines a list of skill sets into a single one
     * @param skillsets The list of skill sets to combin
     * @return The combined skill set
     */
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

    /**
     * Returns the number of skills missing towards the other skill set
     *
     * @param other Other skill set
     * @return The number of skills missing
     */
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

    /**
     *
     * @return The skill counts
     */
    public int[] getSkillCounts() {
        return skillCounts;
    }

    /**
     *
     * @return Whether the skill set is {@link OptionalSkillSet} or {@link SkillSet}
     */
    public boolean isOptional() {
        return false;
    }
}
