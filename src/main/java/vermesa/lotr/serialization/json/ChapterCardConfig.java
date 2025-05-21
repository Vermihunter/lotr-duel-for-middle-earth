package vermesa.lotr.serialization.json;

import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.chapter_cards.ChapterCardColors;
import vermesa.lotr.serialization.json.actions.ActionConfig;

import java.util.List;

public class ChapterCardConfig {
    public int Round;
    public ChapterCardColors Color;
    public SkillsetConfig RequiredSkills;
    public List<ActionConfig> Effects;
    public ChainingSymbols GainedChainingSymbol;
    public ChainingSymbols PlayForFreeChainingSymbol;
    public int CoinsToPlay;
}
