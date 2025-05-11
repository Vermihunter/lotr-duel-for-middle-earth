package vermesa.lotr.serialization.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vermesa.lotr.serialization.json.actions.ActionConfig;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LandmarkTileConfig {
    public String FortressToPlaceTo;
    public SkillsetConfig RequiredSkills;
    public List<ActionConfig> Effects;
}
