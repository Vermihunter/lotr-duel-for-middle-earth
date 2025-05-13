package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "Type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayDiscardedChapterCardActionConfig.class, name = "PlayDiscardedChapterCard"),
        @JsonSubTypes.Type(value = DiscardEnemyGreyCardActionConfig.class, name = "DiscardEnemyGreyCard"),
        @JsonSubTypes.Type(value = MoveOnTheRingQuestTrackActionConfig.class, name = "MoveOnTheRingQuestTrack"),
        @JsonSubTypes.Type(value = MoveUnitsOnCentralBoardActionConfig.class, name = "MoveUnitsOnCentralBoard"),
        @JsonSubTypes.Type(value = GetCoinsFromReserveActionConfig.class, name = "GetCoinsFromReserve"),
        @JsonSubTypes.Type(value = PlaceUnitsOnCentralBoardActionConfig.class, name = "PlaceUnitsOnCentralBoard"),
        @JsonSubTypes.Type(value = RevealAllianceTokensAndChooseSomeActionConfig.class, name = "RevealAllianceTokensAndChooseSome"),
        @JsonSubTypes.Type(value = AddSupportOfARaceActionConfig.class, name = "AddSupportOfARace"),
        @JsonSubTypes.Type(value = GainSkillActionConfig.class, name = "GainSkill"),
        @JsonSubTypes.Type(value = MultiChoiceActionConfig.class, name = "MultiChoice"),
        @JsonSubTypes.Type(value = TakeEnemyCoinsActionConfig.class, name = "TakeEnemyCoins"),
        @JsonSubTypes.Type(value = TakeEnemyUnitFromCentralBoardActionConfig.class, name = "TakeEnemyUnitFromCentralBoard"),
        @JsonSubTypes.Type(value = TakeAnotherTurnActionConfig.class, name = "TakeAnotherTurn"),
        @JsonSubTypes.Type(value = RemoveEnemyFortressActionConfig.class, name = "RemoveEnemyFortress"),
        @JsonSubTypes.Type(value = EventBasedRaceEffectConfig.class, name = "EventBasedRaceEffect"),
        @JsonSubTypes.Type(value = GainAttributeRaceEffectConfig.class, name = "GainAttributeRaceEffect"),
        @JsonSubTypes.Type(value = GainExtraCoinsForDiscardedChapterCardActionConfig.class, name = "GainExtraCoinsForDiscardedChapterCard"),
        @JsonSubTypes.Type(value = OneTimeImmediateRaceEffectConfig.class, name = "OneTimeImmediateRaceEffect"),


})
public abstract class ActionConfig {
    abstract public IAction constructAction(HashMap<String, Region> regionMapper);
}



