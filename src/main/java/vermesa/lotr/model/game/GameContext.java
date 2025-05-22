package vermesa.lotr.model.game;

import vermesa.lotr.model.central_board.CentralBoard;
import vermesa.lotr.model.chapter_cards.ChapterCard;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;
import vermesa.lotr.model.chapter_cards.RoundChapterCardConfig;
import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.landmark_effects.LandmarkTileContext;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingTrack;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the context of the game
 * It contains components that are once configured and never changed
 */
public class GameContext {
    /**
     * The list of all landmark tiles in the game
     */
    private ArrayList<LandmarkTile> landmarkTiles;

    /**
     * The list of round information that the game is played in
     */
    private ArrayList<RoundInformation> roundInformations;

    /**
     * Fellowship player of the game
     */
    private FellowshipPlayer fellowshipPlayer;

    /**
     * Sauron player of the game
     */
    private SauronPlayer sauronPlayer;

    /**
     * The quest of the ring track of the game
     */
    private QuestOfTheRingTrack questOfTheRingTrack;

    /**
     * The central board of the game
     */
    private CentralBoard centralBoard;

    /**
     * The landmark tile context of the game that applies to every landmark tile
     */
    private LandmarkTileContext landmarkTileContext;

    /**
     * The alliance tokens of the game mapped by the race that they belong to
     */
    private HashMap<Race, ArrayList<AllianceToken>> allianceTokens;

    /**
     * Can only be constructed by the Builder class
     */
    private GameContext() {
    }

    /**
     * Getters
     */
    public ArrayList<LandmarkTile> getLandmarkTiles() {
        return landmarkTiles;
    }
    public LandmarkTileContext getLandmarkTileContext() {
        return landmarkTileContext;
    }
    public CentralBoard getCentralBoard() {
        return centralBoard;
    }
    public FellowshipPlayer getFellowshipPlayer() {
        return fellowshipPlayer;
    }
    public SauronPlayer getSauronPlayer() {
        return sauronPlayer;
    }
    public QuestOfTheRingTrack getQuestOfTheRingTrack() {
        return questOfTheRingTrack;
    }
    public ArrayList<RoundInformation> getRoundInformations() {
        return roundInformations;
    }

    /**
     * Builder for the {@link GameContext} class to make sure the different
     * components are constructed according to the rules and without side effects
     */
    public static class Builder {
        ArrayList<Region> regions;
        ArrayList<LandmarkTile> landmarkTiles;
        ArrayList<RoundConfig> roundConfigs;
        FellowshipPlayer fellowshipPlayer;
        SauronPlayer sauronPlayer;
        QuestOfTheRingTrack questOfTheRingTrack;
        LandmarkTileContext landmarkTileContext;
        HashMap<Race, ArrayList<AllianceToken>> allianceTokens;

        /** Hard coded random generator for random events */
        Random rand = new Random(123456);

        public Builder addRegions(ArrayList<Region> regions) {
            this.regions = regions;
            return this;
        }

        public Builder addLandmarkTiles(ArrayList<LandmarkTile> landmarkTiles) {
            this.landmarkTiles = landmarkTiles;
            return this;
        }

        public Builder withPlayers(FellowshipPlayer fellowshipPlayer, SauronPlayer sauronPlayer) {
            this.fellowshipPlayer = fellowshipPlayer;
            this.sauronPlayer = sauronPlayer;
            return this;
        }

        public Builder withRoundConfigs(ArrayList<RoundConfig> roundConfigs) {
            this.roundConfigs = roundConfigs;
            return this;
        }

        public Builder withQuestOfTheRingTrack(QuestOfTheRingTrack questOfTheRingTrack) {
            this.questOfTheRingTrack = questOfTheRingTrack;
            return this;
        }

        public Builder withLandmarkTileContext(LandmarkTileContext landmarkTileContext) {
            this.landmarkTileContext = landmarkTileContext;
            return this;
        }

        public Builder withAllianceTokens(HashMap<Race, ArrayList<AllianceToken>> allianceTokens) {
            this.allianceTokens = allianceTokens;
            return this;
        }

        /**
         * Builds a GameContext with the components configured
         * @return GameContext object that can be used to construct a GameObject
         */
        public GameContext build() {
            GameContext gameContext = new GameContext();
            gameContext.landmarkTiles = this.landmarkTiles;
            Collections.shuffle(gameContext.landmarkTiles, rand);

            gameContext.fellowshipPlayer = this.fellowshipPlayer;
            gameContext.sauronPlayer = this.sauronPlayer;
            gameContext.centralBoard = new CentralBoard(regions);
            gameContext.questOfTheRingTrack = this.questOfTheRingTrack;
            gameContext.allianceTokens = this.allianceTokens;

            allianceTokens.values().forEach(raceAllianceTokens -> Collections.shuffle(raceAllianceTokens, rand));
            gameContext.landmarkTileContext = this.landmarkTileContext;

            allianceTokens.values().forEach(Collections::shuffle);
            gameContext.roundInformations = roundConfigs.stream()
                    .map(this::createRoundInformation)
                    .collect(Collectors.toCollection(ArrayList::new));


            return gameContext;
        }

        /**
         * Creates a RoundInformation object from a RoundConfig
         * Initializes ChapterCard IDs randomly
         *
         * @param roundConfig Configuration to use
         * @return RoundInformation with initialized ChapterCard IDs
         */
        private RoundInformation createRoundInformation(RoundConfig roundConfig) {
            var chapterCards = initializeChapterCards(roundConfig.roundChaptercardsContexts);

            RoundChapterCardConfig roundChapterCardConfig = new RoundChapterCardConfig(roundConfig.chapterCardConfigs);
            RoundChapterCardSet cardSet = RoundChapterCardSet.from(chapterCards, roundChapterCardConfig);
            return new RoundInformation(cardSet, roundConfig.coinsPerChapterCardDiscard);
        }

        /**
         * Assigns shuffled IDs to all the chapter cards that will determine the order
         * which they are displayed in the game rounds
         *
         * @param chapterCardContexts The chapter cards to initialize
         */
        private ArrayList<ChapterCard> initializeChapterCards(ArrayList<ChapterCardContext> chapterCardContexts) {
            var IDStream = IntStream
                    .range(0, chapterCardContexts.size())
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));

            Collections.shuffle(IDStream, rand);

            return IntStream.range(0, chapterCardContexts.size())
                    .mapToObj(i -> new ChapterCard(IDStream.get(i), chapterCardContexts.get(i)))
                    .collect(Collectors.toCollection(ArrayList::new));

        }
    }

}
