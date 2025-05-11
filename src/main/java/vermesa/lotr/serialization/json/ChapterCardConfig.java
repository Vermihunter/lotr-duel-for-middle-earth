package vermesa.lotr.serialization.json;

import vermesa.lotr.model.ChainingSymbol;
import vermesa.lotr.model.chapter_cards.ChapterCardColors;
import vermesa.lotr.serialization.json.actions.ActionConfig;

import java.util.List;

public class ChapterCardConfig {
    public int Round;
    public ChapterCardColors Color;
    public SkillsetConfig RequiredSkills;
    public List<ActionConfig> Effects;
    public ChainingSymbol ChainingSymbol;
}
