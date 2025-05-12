package vermesa.lotr.model.skills;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements the Composite design pattern to make the instance look
 * as a single skill set.
 * <p>
 * Every instance in the fixedSkillSets and in the optionalSkillSets
 * lists is a SkillSet instance of a Grey chapter card.
 */
public class ExtendedSkillSet {
    /**
     * Results of grey chapter cards that offer all the skills present on the card
     */
    private final ArrayList<SkillSet> fixedSkillSets;

    /**
     * Results of grey chapter cards that offer to CHOOSE ANY ONE SKILL of the skills present on the card
     */
    private final ArrayList<OptionalSkillSet> optionalSkillSets;

    public ExtendedSkillSet() {
        this.fixedSkillSets = new ArrayList<>();
        this.optionalSkillSets = new ArrayList<>();
    }

    /**
     * Removes a SkillSet if present
     *
     * @param skillSet The SkillSet to be removed
     * @return Returns whether the requested skill set to be removed was present or not
     */
    public boolean discardSkillSet(SkillSet skillSet) {

        if (fixedSkillSets.remove(skillSet)) {
            return true;
        }

        return optionalSkillSets.remove(skillSet);
    }


    public List<SkillSet> getAllSkillSets() {
        return Stream
                .concat(fixedSkillSets.stream(), optionalSkillSets.stream())
                .collect(Collectors.toList());
    }

    public void addSkillSet(SkillSet skillSet) {
        if (skillSet instanceof OptionalSkillSet) {
            optionalSkillSets.add((OptionalSkillSet) skillSet);
        } else {
            fixedSkillSets.add(skillSet);
        }
    }

    public int missingSkills(SkillSet other) {


        return 0;
    }
}
