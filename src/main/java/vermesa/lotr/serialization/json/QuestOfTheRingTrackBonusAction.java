package vermesa.lotr.serialization.json;

import vermesa.lotr.serialization.json.actions.ActionConfig;

import java.util.List;

public record QuestOfTheRingTrackBonusAction(int Position, List<ActionConfig> BonusActions) {
}
