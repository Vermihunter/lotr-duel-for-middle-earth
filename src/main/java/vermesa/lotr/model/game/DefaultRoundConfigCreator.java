package vermesa.lotr.model.game;

import vermesa.lotr.model.actions.central_board_actions.ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion;
import vermesa.lotr.model.actions.chapter_card_actions.GainSkillAction;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.model.actions.race_effect_actions.AddSupportOfARaceAction;
import vermesa.lotr.model.actions.ring_quest_track_actions.MoveOnTheRingQuestTrackAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.chapter_cards.ChapterCardColors;
import vermesa.lotr.model.chapter_cards.ChapterCardConfigBuilder;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.SkillSet;

import java.util.ArrayList;
import java.util.List;

public class DefaultRoundConfigCreator {
    public static ArrayList<RoundConfig> getDefaultRoundConfigs(List<Region> regions) {
        ArrayList<RoundConfig> defaultRoundConfigs = new ArrayList<>();

        defaultRoundConfigs.add(createDefaultFirstRoundConfig(regions));
        defaultRoundConfigs.add(createDefaultSecondRoundConfig());
        defaultRoundConfigs.add(createDefaultThirdRoundConfig());

        return defaultRoundConfigs;
    }

    private static RoundConfig createDefaultFirstRoundConfig(List<Region> regions) {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        // Green cards
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 1, 0}),
                ChainingSymbols.HARP,
                null,
                new ArrayList<>() {{
                    new AddSupportOfARaceAction(Race.Elves);
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 1}),
                ChainingSymbols.ANVIL,
                null,
                new ArrayList<>() {{
                    new AddSupportOfARaceAction(Race.Dwarves);
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 1}),
                ChainingSymbols.POT,
                null,
                new ArrayList<>() {{
                    new AddSupportOfARaceAction(Race.Hobbits);
                }},
                0,
                ChapterCardColors.GREEN
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 1, 0}),
                ChainingSymbols.HORSESHOE,
                null,
                new ArrayList<>() {{
                    new AddSupportOfARaceAction(Race.Humans);
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
                    new MoveOnTheRingQuestTrackAction(1);
                }},
                1,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 1, 0, 0}),
                ChainingSymbols.BACKPACK,
                null,
                new ArrayList<>() {{
                    new MoveOnTheRingQuestTrackAction(1);
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 1, 0, 0, 0}),
                ChainingSymbols.HORSE,
                null,
                new ArrayList<>() {{
                    new MoveOnTheRingQuestTrackAction(1);
                }},
                0,
                ChapterCardColors.BLUE
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new MoveOnTheRingQuestTrackAction(1);
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
                    new GetCoinsFromReserveAction(2);
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GetCoinsFromReserveAction(2);
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GetCoinsFromReserveAction(2);
                }},
                0,
                ChapterCardColors.YELLOW
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GetCoinsFromReserveAction(2);
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
                    new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 0, 1}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{0, 0, 0, 1, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{0, 1, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{0, 1, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{0, 0, 0, 0, 0}),
                null,
                null,
                new ArrayList<>() {{
                    new GainSkillAction(new SkillSet(
                            new int[]{1, 0, 0, 0, 0}
                    ));
                }},
                0,
                ChapterCardColors.GREY
        ));

        // Red cards
        /*
        chapterCardContexts.add(new ChapterCardContext(
                new SkillSet(new int[]{1, 0, 0, 0, 0 }),
                null,
                null,
                new ArrayList<>(){{
                    new ChooseWhereToPlaceUnitsOnCentralBoardAction_AllToSameRegion()
                }},
                0,
                ChapterCardColors.RED
        ));
        */


        return new RoundConfig(chapterCardConfigBuilders, 1, chapterCardContexts);
    }

    private static RoundConfig createDefaultSecondRoundConfig() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        return new RoundConfig(chapterCardConfigBuilders, 2, chapterCardContexts);
    }

    private static RoundConfig createDefaultThirdRoundConfig() {
        ArrayList<ChapterCardConfigBuilder> chapterCardConfigBuilders = new ArrayList<>();

        ArrayList<ChapterCardContext> chapterCardContexts = new ArrayList<>();

        return new RoundConfig(chapterCardConfigBuilders, 3, chapterCardContexts);
    }
}
