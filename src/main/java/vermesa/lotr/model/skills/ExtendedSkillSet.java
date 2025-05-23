package vermesa.lotr.model.skills;

import java.util.*;
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
        fixedSkillSets.add(skillSet);
    }

    public void addOptionalSkillSet(OptionalSkillSet skillSet) {
        fixedSkillSets.add(skillSet);
    }

    public int missingSkills(SkillSet required) {
        SkillSet fixed = combineFixedSkillSets();
        int[] have = fixed.getSkillCounts();
        int[] need = required.getSkillCounts();
        int dims = have.length;

        // 2) Compute initial deficits and their sum
        int[] deficits = new int[dims];
        int totalDef = 0;
        for (int i = 0; i < dims; i++) {
            deficits[i] = Math.max(0, need[i] - have[i]);
            totalDef += deficits[i];
        }

        if (totalDef == 0) {
            // already satisfy all requirements just from fixed sets
            return 0;
        }

        // 3) Run a small DP/“state‐space search” over your optionalSkillSets
        //    Each optional group can be used to reduce ONE skill's deficit.
        //    We track all reachable deficit‐vectors as we consume each optional group.
        Set<List<Integer>> states = new HashSet<>();
        states.add(Arrays.stream(deficits).boxed().collect(Collectors.toList()));

        for (SkillSet opt : optionalSkillSets) {
            int[] bonus = opt.getSkillCounts();
            Set<List<Integer>> next = new HashSet<>();

            for (List<Integer> st : states) {
                // a) choose *not* to use this optional at all
                next.add(st);

                // b) use it to cut down one of the remaining deficits
                for (int i = 0; i < dims; i++) {
                    int b = bonus[i];
                    if (b <= 0 || st.get(i) == 0) continue;
                    List<Integer> copy = new ArrayList<>(st);
                    copy.set(i, Math.max(0, copy.get(i) - b));
                    next.add(copy);
                }
            }
            states = next;
        }

        // 4) Of all final “deficit‐vectors,” pick the one whose sum is smallest
        return states.stream()
                .mapToInt(v -> v.stream().mapToInt(Integer::intValue).sum())
                .min()
                .orElse(totalDef); // 0 if you covered everything
    }

    private SkillSet combineFixedSkillSets() {
        int length = Skill.values().length;
        int[] combined = new int[length];

        // Sum them up
        for (SkillSet ss : fixedSkillSets) {
            int[] counts = ss.getSkillCounts();
            for (int i = 0; i < length; i++) {
                combined[i] += counts[i];
            }
        }

        // Construct a new SkillSet; assume constructor defensively copies the array
        return new SkillSet(combined);
    }
}
