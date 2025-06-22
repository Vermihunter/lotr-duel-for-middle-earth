package vermesa.lotr.serialization.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.chapter_cards.ChapterCardConfigBuilder;
import vermesa.lotr.model.chapter_cards.ChapterCardContext;
import vermesa.lotr.model.game.*;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.landmark_effects.LandmarkTileContext;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;
import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingBonusAction;
import vermesa.lotr.model.quest_of_the_ring_track.QuestOfTheRingTrack;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.utils.StringToPlayer;
import vermesa.lotr.serialization.IGameConfig;
import vermesa.lotr.model.central_board.Region;

import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonConfig implements IGameConfig {

    public InitialConfig InitialConfig;
    public String StartingPlayer;
    public int TotalCoinCount;
    public ArrayList<RoundConfig> Rounds;
    public ArrayList<LandmarkTileConfig> LandmarkTilesToUse;
    public ArrayList<ChapterCardConfig> ChapterCardsToUse;
    public ArrayList<RegionConfig> Regions;
    public QuestOfTheRingConfig QuestOfTheRingTrackConfig;
    public ArrayList<RaceConfig> Races;
    public LandmarkTileContextConfig LandmarkTileContextConfig;

    private HashMap<String, Region> regionsByName;

    public Game createGame(Random rand) {
        regionsByName = new HashMap<>();

        Player sauronPlayer = new Player(
                PlayerType.Sauron,
                InitialConfig.SauronPlayer.Coins,
                InitialConfig.SauronPlayer.Units,
                InitialConfig.SauronPlayer.Towers);

        Player fellowshipPlayer = new Player(
                PlayerType.Fellowship,
                InitialConfig.FellowshipPlayer.Coins,
                InitialConfig.FellowshipPlayer.Units,
                InitialConfig.FellowshipPlayer.Towers);

        var regions = constructRegions();
        var landmarkTiles = constructLandmarkTiles();
        Collections.shuffle(landmarkTiles, rand);

        var roundConfigs = constructRoundConfigs();
        var questOfTheRingTrack = constructQuestOfTheRingTrack();
        var landmarkTileContext = new LandmarkTileContext(LandmarkTileContextConfig.CoinPerAlreadyPlacedFortressPawn, LandmarkTileContextConfig.LandmarkTilesAtTime);
        var allianceTokens = constructAllianceTokens();
        allianceTokens.values().forEach(raceAllianceTokens -> Collections.shuffle(raceAllianceTokens, rand));


        var contextBuilder = new GameContext.Builder()
            .addRegions(regions)
            .addLandmarkTiles(landmarkTiles)
            .withPlayers(fellowshipPlayer, sauronPlayer)
            .withRoundConfigs(roundConfigs)
            .withQuestOfTheRingTrack(questOfTheRingTrack)
            .withLandmarkTileContext(landmarkTileContext)
            .withAllianceTokens(allianceTokens);

        var context = contextBuilder.build(new Random(12345), DefaultGameContextBuilder.buildDefaultBuilderConfig());
        var state = constructGameState(sauronPlayer, fellowshipPlayer, context);


        return new Game(context, state);
    }

    private HashMap<Race, ArrayList<AllianceToken>> constructAllianceTokens() {
        HashMap<Race, ArrayList<AllianceToken>> combined = new HashMap<>();
        for (RaceConfig rc : Races) {
            ArrayList<AllianceToken> allianceTokens = rc.AllianceTokens.stream()
                    .map(actionConfig -> new AllianceToken(actionConfig.constructAction(regionsByName)))
                    .collect(Collectors.toCollection(ArrayList::new));

            combined.put(rc.Name, allianceTokens);
        }

        return combined;
    }

    private QuestOfTheRingTrack constructQuestOfTheRingTrack() {

        var bonusActions = QuestOfTheRingTrackConfig.BonusActions.stream()
                .map(this::constructQuestOfTheRingActions)
                .collect(Collectors.toCollection(ArrayList::new));

        return new QuestOfTheRingTrack(
                QuestOfTheRingTrackConfig.Width,
                QuestOfTheRingTrackConfig.FellowshipPlayerStartPosition,
                QuestOfTheRingTrackConfig.SauronPlayerStartPosition,
                bonusActions);
    }

    private QuestOfTheRingBonusAction constructQuestOfTheRingActions(QuestOfTheRingTrackBonusAction a) {
        return new QuestOfTheRingBonusAction(a.Position(), constructQuestOfTheRingBonusAction(a));
    }

    private List<IAction> constructQuestOfTheRingBonusAction(QuestOfTheRingTrackBonusAction a) {
        return a.BonusActions().stream()
                .map(action -> action.constructAction(regionsByName))
                .toList();
    }

    private ArrayList<vermesa.lotr.model.game.RoundConfig> constructRoundConfigs() {
        return Rounds.stream()
                .map(this::constructRoundConfig)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private vermesa.lotr.model.game.RoundConfig constructRoundConfig(RoundConfig roundConfig) {
        var chapterCardConfigs = roundConfig.ChapterCardConfig.stream()
                .map(c -> new ChapterCardConfigBuilder(c.ID, c.Row, c.DependsOn, c.IsFaceUp))
                .collect(Collectors.toCollection(ArrayList::new));

        var thisRoundChapterCards = ChapterCardsToUse.stream()
                .filter(c -> c.Round == roundConfig.Round)
                .map(this::constructChapterCard)
                .collect(Collectors.toCollection(ArrayList::new));

        return new vermesa.lotr.model.game.RoundConfig(
                chapterCardConfigs,
                roundConfig.CoinsPerChapterCardDiscard,
                thisRoundChapterCards);
    }

    private ChapterCardContext constructChapterCard(ChapterCardConfig c) {
        return new ChapterCardContext(
                c.RequiredSkills.toSkillSet(),
                c.GainedChainingSymbol,
                c.PlayForFreeChainingSymbol,
                c.Effects.stream()
                        .map(e -> e.constructAction(regionsByName))
                        .collect(Collectors.toCollection(ArrayList::new)),
                c.CoinsToPlay,
                c.Color);
    }

    private GameState constructGameState(Player sauronPlayer, Player fellowshipPlayer, GameContext context) {
        Player startingPlayer;
        Player otherPlayer;

        if (StartingPlayer.equals(PlayerType.Sauron.name())) {
            startingPlayer = sauronPlayer;
            otherPlayer = fellowshipPlayer;
        } else if (StartingPlayer.equals(PlayerType.Fellowship.name())) {
            startingPlayer = fellowshipPlayer;
            otherPlayer = sauronPlayer;
        } else {
            throw new IllegalArgumentException("Invalid starting player: " + StartingPlayer);
        }

        int landmarkTilesToUse = context.getLandmarkTileContext().landmarkTilesAtTime();
        ArrayList<LandmarkTile> startingLandmarkTiles = context.getLandmarkTiles().stream()
                .limit(landmarkTilesToUse)
                .collect(Collectors.toCollection(ArrayList::new));


        var startingRoundInformation = context.getRoundInformations().getFirst();
        return GameState.GameStateBuilder.aGameState()
                .withPlayerOnMove(startingPlayer)
                .withNextPlayerOnMove(otherPlayer)
                .withCurrentGameState(CurrentGameState.HAS_NOT_ENDED)
                .withGameContext(context)
                .withCurrentRoundInformation(startingRoundInformation)
                .withCurrentRoundNumber(1)
                .withFollowUpMoves(null)
                .withTotalCoins(TotalCoinCount)
                .withStartingLandmarkTiles(startingLandmarkTiles)
                .withAllianceTokens(context.getAllianceTokens())
                .build();
    }


    private ArrayList<LandmarkTile> constructLandmarkTiles() {
        return LandmarkTilesToUse.stream()
                .map(this::constructLandmarkTile)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private LandmarkTile constructLandmarkTile(LandmarkTileConfig config) {
        List<IAction> actions = config.Effects
                .stream()
                .map(e -> e.constructAction(regionsByName))
                .toList();

        return new LandmarkTile(
                regionsByName.get(config.FortressToPlaceTo),
                config.RequiredSkills.toSkillSet(),
                actions);
    }


    private ArrayList<Region> constructRegions() {
        ArrayList<Region> regions = new ArrayList<>();

        for(RegionConfig config : Regions) {
            Region newRegion = new Region(RegionType.valueOf(config.Name), config.Fortress);
            if (config.StartingState.Fortress != null) {
                newRegion.placeFortress(StringToPlayer.fromString(config.Fortress));
            }

            if (config.StartingState.Units.Player != null) {
                newRegion.addUnits(StringToPlayer.fromString(config.StartingState.Units.Player), config.StartingState.Units.Count);
            }

            regions.add(newRegion);

            regionsByName.put(config.Name, newRegion);
        }

        for(RegionConfig config : Regions) {
            Region currRegion = regionsByName.get(config.Name);
            for(String connectedRegionName : config.ConnectedWith) {
                Region connectedRegion = regionsByName.get(connectedRegionName);
                currRegion.addConnectedRegion(connectedRegion);
            }
        }

        return regions;
    }
}
