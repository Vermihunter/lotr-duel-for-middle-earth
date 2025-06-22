package vermesa.lotr.model.race_effects;

import vermesa.lotr.model.actions.basic_actions.MultiChoiceAction;
import vermesa.lotr.model.actions.basic_actions.TakeAnotherTurnAction;
import vermesa.lotr.model.actions.central_board_actions.*;
import vermesa.lotr.model.actions.chapter_card_actions.PlayDiscardedChapterCardAction;
import vermesa.lotr.model.actions.coin_actions.GainExtraCoinsForDiscardedChapterCardAction;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.actions.coin_actions.TakeEnemyCoinsAction;
import vermesa.lotr.model.actions.race_effect_actions.*;
import vermesa.lotr.model.actions.ring_quest_track_actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.model.central_board.Region;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultAllianceTokenCreator {

    public static ArrayList<AllianceToken> createDefaultAllianceTokenForRace(Race race, List<Region> regions) {
        ArrayList<AllianceToken> allianceTokens = new ArrayList<>();

        switch (race) {
            case Elves -> {
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAPTER_CARD_YELLOW_USED, new TakeAnotherTurnAction())));
                allianceTokens.add(new AllianceToken(new GainAttributeRaceEffect(RaceEffectAttributes.PLACE_ANYWHERE_FOR_RED_CARD)));
                allianceTokens.add(new AllianceToken(new GainAttributeRaceEffect(RaceEffectAttributes.BENEFIT_FROM_ANY_SKILL)));
            }
            case Ents -> {
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new TakeAnotherTurnAction())));
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new ChooseEnemyFortressToRemoveAction())));
                allianceTokens.add(new AllianceToken(new MultiChoiceAction(
                        List.of(
                                new MultiChoiceAction(
                                        List.of(
                                                new TakeEnemyUnitFromCentralBoardCollectingAction(1),
                                                new TakeEnemyCoinsAction(1),
                                                new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 1)
                                        ), 1
                                ),
                                new MultiChoiceAction(
                                        List.of(
                                                new TakeEnemyUnitFromCentralBoardCollectingAction(1),
                                                new TakeEnemyCoinsAction(1),
                                                new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 1)
                                        ), 1
                                ), new MultiChoiceAction(
                                        List.of(
                                                new TakeEnemyUnitFromCentralBoardCollectingAction(1),
                                                new TakeEnemyCoinsAction(1),
                                                new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 1)
                                        ), 1
                                )
                        ), 3
                )));
            }
            case Hobbits -> {
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new AddSupportOfARaceAction(Race.Eagles))));
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAPTER_CARD_BLUE_USED, new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(regions, 1))));
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAINING_SYMBOL_USED, new GetCoinsFromReserveAction(3))));
            }
            case Humans -> {
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAPTER_CARD_YELLOW_USED, new MoveOnTheRingQuestTrackAction(1))));
                allianceTokens.add(new AllianceToken(new GainAttributeRaceEffect(RaceEffectAttributes.PLACE_EXTRA_UNIT_FOR_RED_CARD)));
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAPTER_CARD_DISCARDED, new GainExtraCoinsForDiscardedChapterCardAction(new int[]{1, 2, 3}))));
            }
            case Dwarves -> {
                allianceTokens.add(new AllianceToken(new GainAttributeRaceEffect(RaceEffectAttributes.IGNORE_LANDMARK_TILE_COST)));
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.LANDMARK_TILE_USED, new TakeAnotherTurnAction())));
                allianceTokens.add(new AllianceToken(new EventBasedRaceEffect(RaceEffectCallbackEventType.CHAPTER_CARD_GREEN_USED, new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 2))));
            }
            case Wizards -> {
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new MoveOnTheRingQuestTrackAction(2))));
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new ChooseWhereToPlaceUnitsOnCentralBoardAction_EveryUnitFreely(regions, 2))));
                allianceTokens.add(new AllianceToken(new OneTimeImmediateRaceEffect(new PlayDiscardedChapterCardAction())));
            }
            case Eagles -> {
                // Empty
            }
        }

        return allianceTokens;
    }

    public static HashMap<Race, ArrayList<AllianceToken>> createDefaultAllianceTokens(List<Region> regions) {
        return Arrays.stream(Race.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        race -> createDefaultAllianceTokenForRace(race, regions),
                        (a, b) -> a,                               // merge fn (never happens here)
                        HashMap::new
                ));
    }
}
