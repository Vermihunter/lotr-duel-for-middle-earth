package vermesa.lotr.model.quest_of_the_ring_track;

import vermesa.lotr.model.actions.basic_actions.TakeAnotherTurnAction;
import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.central_board.Region;

import java.util.List;

public class DefaultQuestOfTheRingTrackCreator {

    public static QuestOfTheRingTrack createQuestOfTheRingTrack(List<Region> regions) {
        List<QuestOfTheRingBonusAction> bonusActions = List.of(
                new QuestOfTheRingBonusAction(3, List.of(new GetCoinsFromReserveAction(1))),
                new QuestOfTheRingBonusAction(6, List.of(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(regions, 1))),
                new QuestOfTheRingBonusAction(9, List.of(new TakeAnotherTurnAction()))
        );

        return new QuestOfTheRingTrack(28, 14, 0, bonusActions);
    }
}
