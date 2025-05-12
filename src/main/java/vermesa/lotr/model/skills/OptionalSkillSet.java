package vermesa.lotr.model.skills;

public class OptionalSkillSet extends SkillSet {
    public OptionalSkillSet(int[] skillCounts) {
        super(skillCounts);
    }

    @Override
    public boolean isOptional() {
        return true;
    }
}
