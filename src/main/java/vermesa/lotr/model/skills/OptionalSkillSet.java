package vermesa.lotr.model.skills;

/**
 * Some grey chapter cards offer optional skills meaning that every round
 * the player can choose which one of the offered skills wants to choose
 */
public class OptionalSkillSet extends SkillSet {
    public OptionalSkillSet(int[] skillCounts) {
        super(skillCounts);
    }

    @Override
    public boolean isOptional() {
        return true;
    }
}
