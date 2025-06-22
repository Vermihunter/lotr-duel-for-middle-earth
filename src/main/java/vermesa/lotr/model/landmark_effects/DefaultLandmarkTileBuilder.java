package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.central_board_actions.*;
import vermesa.lotr.model.actions.chapter_card_actions.CreateDiscardEnemyGreyCardActionsAction;
import vermesa.lotr.model.actions.chapter_card_actions.DiscardEnemyGreyCardAction;
import vermesa.lotr.model.actions.chapter_card_actions.PlayDiscardedChapterCardAction;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.actions.race_effect_actions.RevealAllianceTokensAndChooseSomeAction;
import vermesa.lotr.model.actions.race_effect_actions.TakeTopTwoAllianceTokensChooseOneAction;
import vermesa.lotr.model.actions.ring_quest_track_actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.SkillSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultLandmarkTileBuilder {
    public static LandmarkTile buildDefaultLandmarkTile(Region region) {
        int[] requiredSkills;
        List<IAction> actions = new ArrayList<>();

        switch (region.getRegionType()) {
            case Arnor -> {
                requiredSkills = new int[]{0, 3, 2, 0, 1};
                actions.add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(region), 2));
                actions.add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 2));
            }
            case Lindon -> {
                requiredSkills = new int[]{0, 3, 0, 1, 2};
                actions.add(new TakeTopTwoAllianceTokensChooseOneAction(Race.values(), 2, 1));
            }
            case Enedwaith -> {
                requiredSkills = new int[]{2, 0, 3, 1, 0};
                actions.add(new CreateDiscardEnemyGreyCardActionsAction());
                actions.add(new MoveOnTheRingQuestTrackAction(1));
            }
            case Rohan -> {
                requiredSkills = new int[]{3, 0, 0, 1, 2};
                actions.add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(region), 3));
            }
            case Gondor -> {
                requiredSkills = new int[]{3, 1, 0, 2, 0};
                actions.add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(region), 1));
                actions.add(new MoveOnTheRingQuestTrackAction(2));
            }
            case Mordor -> {
                requiredSkills = new int[]{0, 0, 3, 1, 2};
                actions.add(new PlayDiscardedChapterCardAction());
            }
            case Rhovanion -> {
                requiredSkills = new int[]{1, 2, 1, 1, 1};
                actions.add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 1));
                actions.add(new GetCoinsFromReserveAction(5));
            }

            default -> throw new IllegalStateException("Unexpected value: " + region.getRegionType());
        }

        return new LandmarkTile(region, new SkillSet(requiredSkills), actions);
    }

    public static ArrayList<LandmarkTile> buildDefaultLandmarkTiles(List<Region> regions) {
        return regions.stream()
                .map(DefaultLandmarkTileBuilder::buildDefaultLandmarkTile)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static LandmarkTileContext buildDefaultLandmarkTileContext() {
        return new LandmarkTileContext(1, 3);
    }
}
