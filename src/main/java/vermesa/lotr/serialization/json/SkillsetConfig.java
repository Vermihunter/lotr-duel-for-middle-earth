package vermesa.lotr.serialization.json;

import vermesa.lotr.model.skills.OptionalSkillSet;
import vermesa.lotr.model.skills.SkillSet;

public class SkillsetConfig {
    public int Ruse;
    public int Strength;
    public int Courage;
    public int Knowledge;
    public int Leadership;
    public boolean IsOptional = false;

    public SkillSet toSkillSet() {
        var values = new int[]{Ruse, Strength, Courage, Knowledge, Leadership};

        return IsOptional
                ? new OptionalSkillSet(values)
                : new SkillSet(values);
    }
}
