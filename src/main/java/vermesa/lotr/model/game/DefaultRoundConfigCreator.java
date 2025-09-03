package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.central_board_actions.*;
import vermesa.lotr.model.actions.chapter_card_actions.GainSkillAction;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.actions.coin_actions.TakeEnemyCoinsAction;
import vermesa.lotr.model.actions.race_effect_actions.AddSupportOfARaceAction;
import vermesa.lotr.model.actions.ring_quest_track_actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.chapter_cards.ChapterCardColors;
import vermesa.lotr.model.chapter_cards.ChapterCardConfigBuilder;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.OptionalSkillSet;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.utils.RegionUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultRoundConfigCreator {
    public static ArrayList<RoundConfig> getDefaultRoundConfigs(List<Region> regions) {
        ArrayList<RoundConfig> defaultRoundConfigs = new ArrayList<>();

        defaultRoundConfigs.add(createDefaultFirstRoundConfig(regions));
        defaultRoundConfigs.add(createDefaultSecondRoundConfig(regions));
        defaultRoundConfigs.add(createDefaultThirdRoundConfig(regions));

        return defaultRoundConfigs;
    }

    private static RoundConfig createDefaultFirstRoundConfig(List<Region> regions) {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = getFirstRoundChapterCardConfigBuilders();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        // Green cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 1, 0}),
                ChainingSymbols.HARP,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Elves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 1}),
                ChainingSymbols.ANVIL,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Dwarves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 1}),
                ChainingSymbols.POT,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Hobbits));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 1, 0}),
                ChainingSymbols.HORSESHOE,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Humans));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        // Blue cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                ChainingSymbols.FISH,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                1,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 1, 0, 0}),
                ChainingSymbols.BACKPACK,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 1, 0, 0, 0}),
                ChainingSymbols.HORSE,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        // Yellow
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(2));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(2));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(2));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(2));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        // Grey cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 0, 1}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 1, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 1, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 1, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        // Red cards
        Region lindon = RegionUtils.findByType(regions, RegionType.Lindon);
        Region gondor = RegionUtils.findByType(regions, RegionType.Gondor);
        Region rhovanion = RegionUtils.findByType(regions, RegionType.Rhovanion);
        Region enedwaith = RegionUtils.findByType(regions, RegionType.Enedwaith);
        Region arnor = RegionUtils.findByType(regions, RegionType.Arnor);
        Region rohan = RegionUtils.findByType(regions, RegionType.Rohan);

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 0, 0, 0, 0 }),
                ChainingSymbols.ARROW,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(gondor, rohan), 1));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                ChainingSymbols.HELMET,
                null,
                new ArrayList<>(){{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(enedwaith, rhovanion), 1));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                ChainingSymbols.KNIFE,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(lindon, arnor), 1));
                }},
                0,
                ChapterCardColors.RED
        ));



        return new RoundConfig(chapterCardConfigBuilders, 1, chapterCardContexts);
    }

    private static ArrayList<ChapterCardConfigBuilder> getFirstRoundChapterCardConfigBuilders() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(0, 1, new ArrayList<>() {{
            add(2);
            add(3);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(1, 1, new ArrayList<>() {{
            add(3);
            add(4);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(2, 2, new ArrayList<>() {{
            add(5);
            add(6);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(3, 2, new ArrayList<>() {{
            add(6);
            add(7);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(4, 2, new ArrayList<>() {{
            add(7);
            add(8);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(5, 3, new ArrayList<>() {{
            add(9);
            add(10);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(6, 3, new ArrayList<>() {{
            add(10);
            add(11);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(7, 3, new ArrayList<>() {{
            add(11);
            add(12);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(8, 3, new ArrayList<>() {{
            add(12);
            add(13);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(9, 4, new ArrayList<>() {{
            add(14);
            add(15);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(10, 4, new ArrayList<>() {{
            add(15);
            add(16);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(11, 4, new ArrayList<>() {{
            add(16);
            add(17);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(12, 4, new ArrayList<>() {{
            add(17);
            add(18);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(13, 4, new ArrayList<>() {{
            add(18);
            add(19);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(14, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(15, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(16, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(17, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(18, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(19, 5, new ArrayList<>(), true));
        return chapterCardConfigBuilders;
    }

    private static RoundConfig createDefaultSecondRoundConfig(List<Region> regions) {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(0, 1, new ArrayList<>() {{
            add(6);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(1, 1, new ArrayList<>() {{
            add(6);
            add(7);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(2, 1, new ArrayList<>() {{
            add(7);
            add(8);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(3, 1, new ArrayList<>() {{
            add(8);
            add(9);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(4, 1, new ArrayList<>() {{
            add(9);
            add(10);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(5, 1, new ArrayList<>() {{
            add(10);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(6, 2, new ArrayList<>() {{
            add(11);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(7, 2, new ArrayList<>() {{
            add(11);
            add(12);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(8, 2, new ArrayList<>() {{
            add(12);
            add(13);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(9, 2, new ArrayList<>() {{
            add(13);
            add(14);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(10, 2, new ArrayList<>() {{
            add(14);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(11, 3, new ArrayList<>() {{
            add(15);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(12, 3, new ArrayList<>() {{
            add(15);
            add(16);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(13, 3, new ArrayList<>() {{
            add(16);
            add(17);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(14, 3, new ArrayList<>() {{
            add(17);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(15, 4, new ArrayList<>() {{
            add(18);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(16, 4, new ArrayList<>() {{
            add(18);
            add(19);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(17, 4, new ArrayList<>() {{
            add(19);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(18, 5, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(19, 5, new ArrayList<>(), true));


        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();
        // Green cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 1, 1, 1}),
                null,
                ChainingSymbols.HARP,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Elves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 0, 0, 1}),
                ChainingSymbols.ACORN,
                ChainingSymbols.POT,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Hobbits));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 0, 1}),
                null,
                ChainingSymbols.ANVIL,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Dwarves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 2, 1, 0}),
                ChainingSymbols.SCROLL,
                ChainingSymbols.HORSESHOE,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Elves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        // Blue cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                ChainingSymbols.BACKPACK,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                1,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 1, 1, 0, 0}),
                null,
                ChainingSymbols.HORSE,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 1, 2, 0, 0}),
                ChainingSymbols.FIRE,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 1, 0}),
                ChainingSymbols.BEDROLL,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 1, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(1));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        // Yellow
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                ChainingSymbols.TREASURE_CASKET,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(3));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(4));
                }},
                0,
                ChapterCardColors.YELLOW
        ));


        // Grey cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 1, 0}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 0, 1}
                    )));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 2, 0, 0}
                    )));
                }},
                1,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{0, 2, 0, 0, 0}
                    )));
                }},
                1,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new SkillSet(
                            new int[]{2, 0, 0, 0, 0}
                    )));
                }},
                1,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new OptionalSkillSet(
                            new int[]{1, 0, 0, 1, 1}
                    )));
                }},
                1,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 1, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GainSkillAction(new OptionalSkillSet(
                            new int[]{1, 1, 1, 0, 0}
                    )));
                }},
                1,
                ChapterCardColors.GREY
        ));

        // Red cards
        Region lindon = RegionUtils.findByType(regions, RegionType.Lindon);
        Region gondor = RegionUtils.findByType(regions, RegionType.Gondor);
        Region rhovanion = RegionUtils.findByType(regions, RegionType.Rhovanion);
        Region enedwaith = RegionUtils.findByType(regions, RegionType.Enedwaith);
        Region arnor = RegionUtils.findByType(regions, RegionType.Arnor);
        Region rohan = RegionUtils.findByType(regions, RegionType.Rohan);
        Region mordor = RegionUtils.findByType(regions, RegionType.Mordor);

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 1, 0, 0}),
                ChainingSymbols.ARMOR,
                ChainingSymbols.HELMET,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(lindon, enedwaith), 2));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 0, 1, 0}),
                ChainingSymbols.AXE,
                ChainingSymbols.KNIFE,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(mordor, rohan), 2));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{3, 0, 0, 0, 0}),
                null,
                ChainingSymbols.ARROW,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(gondor, mordor), 2));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 1, 1, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(lindon, rohan), 2));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 2, 0, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(arnor, rhovanion), 2));
                }},
                0,
                ChapterCardColors.RED
        ));



        return new RoundConfig(chapterCardConfigBuilders, 2, chapterCardContexts);
    }

    private static RoundConfig createDefaultThirdRoundConfig(List<Region> regions) {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(0, 1, new ArrayList<>() {{
            add(2);
            add(3);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(1, 1, new ArrayList<>() {{
            add(3);
            add(4);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(2, 2, new ArrayList<>() {{
            add(5);
            add(6);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(3, 2, new ArrayList<>() {{
            add(6);
            add(7);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(4, 2, new ArrayList<>() {{
            add(7);
            add(8);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(5, 3, new ArrayList<>() {{
            add(9);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(6, 3, new ArrayList<>() {{
            add(9);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(7, 3, new ArrayList<>() {{
            add(10);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(8, 3, new ArrayList<>() {{
            add(10);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(9, 4, new ArrayList<>() {{
            add(11);
            add(12);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(10, 4, new ArrayList<>() {{
            add(13);
            add(14);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(11, 5, new ArrayList<>() {{
            add(15);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(12, 5, new ArrayList<>() {{
            add(15);
            add(16);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(13, 5, new ArrayList<>() {{
            add(16);
            add(17);
        }}, true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(14, 5, new ArrayList<>() {{
            add(17);
        }}, true));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(15, 6, new ArrayList<>() {{
            add(18);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(16, 6, new ArrayList<>() {{
            add(18);
            add(19);
        }}, false));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(17, 6, new ArrayList<>() {{
            add(19);
        }}, false));

        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(18, 7, new ArrayList<>(), true));
        chapterCardConfigBuilders.add(new ChapterCardConfigBuilder(19, 7, new ArrayList<>(), true));


        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        // Green cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 0, 0, 2}),
                null,
                ChainingSymbols.SCROLL,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Wizards));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 1, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Wizards));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 2, 0}),
                null,
                ChainingSymbols.ACORN,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Ents));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 2, 1, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new AddSupportOfARaceAction(Race.Dwarves));
                }},
                0,
                ChapterCardColors.GREEN
        ));

        // Blue cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 3, 0, 0, 0}),
                null,
                ChainingSymbols.FIRE,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 2, 0, 0}),
                null,
                ChainingSymbols.FISH,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{3, 0, 1, 0, 0}),
                null,
                ChainingSymbols.BEDROLL,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(3));
                }},
                3,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 0, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new MoveOnTheRingQuestTrackAction(2));
                }},
                0,
                ChapterCardColors.BLUE
        ));

        // Yellow
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 0, 0, 1, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(5));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 1, 0, 0}),
                null,
                ChainingSymbols.TREASURE_CASKET,
                new ArrayList<>() {{
                    add(new GetCoinsFromReserveAction(5));
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        // Red cards
        Region lindon = RegionUtils.findByType(regions, RegionType.Lindon);
        Region gondor = RegionUtils.findByType(regions, RegionType.Gondor);
        Region rhovanion = RegionUtils.findByType(regions, RegionType.Rhovanion);
        Region enedwaith = RegionUtils.findByType(regions, RegionType.Enedwaith);
        Region arnor = RegionUtils.findByType(regions, RegionType.Arnor);
        Region rohan = RegionUtils.findByType(regions, RegionType.Rohan);
        Region mordor = RegionUtils.findByType(regions, RegionType.Mordor);

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 2, 0, 0}),
                null,
                ChainingSymbols.ARMOR,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(rohan, enedwaith), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 1, 0, 1, 2}),
                null,
                ChainingSymbols.AXE,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(gondor, arnor), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 2, 2, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(mordor, rhovanion), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 1, 2, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(lindon, mordor), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{3, 0, 0, 0, 2}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(rhovanion, gondor), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{2, 0, 2, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion(List.of(arnor, enedwaith), 3));
                }},
                0,
                ChapterCardColors.RED
        ));

        // Purple cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 1, 1, 1, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new TakeEnemyCoinsAction(1));
                    add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 2));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 2, 0, 0, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new TakeEnemyUnitFromCentralBoardCollectingAction(1));
                    add(new TakeEnemyCoinsAction(1));
                    add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 1));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 3, 0, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new TakeEnemyUnitFromCentralBoardCollectingAction(1));
                    add(new TakeEnemyCoinsAction(2));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 1, 1, 2, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new TakeEnemyUnitFromCentralBoardCollectingAction(2));
                    add(new TakeEnemyCoinsAction(1));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 3, 0, 1, 0}),
                null,
                null,
                new ArrayList<>() {{
                    add(new TakeEnemyUnitFromCentralBoardCollectingAction(1));
                    add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 2));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 2, 1, 1}),
                null,
                null,
                new ArrayList<>() {{
                    add(new ChooseUnitsSourceToMoveAction(CentralBoardUnitMoveStrategy.NEIGHBORING, 3));
                }},
                0,
                ChapterCardColors.PURPLE
        ));

        return new RoundConfig(chapterCardConfigBuilders, 3, chapterCardContexts);
    }
}
