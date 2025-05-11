package vermesa.lotr.serialization.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vermesa.lotr.model.*;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.serialization.IGameConfig;
import vermesa.lotr.model.central_board.Region;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonConfig implements IGameConfig {
    public InitialConfig InitialConfig;
    public String StartingPlayer;
    public int TotalCoinCount;
    public List<RoundConfig> Rounds;
    public List<LandmarkTileConfig> LandmarkTilesToUse;
    public List<ChapterCardConfig> ChapterCardsToUse;
    public List<RegionConfig> Regions;

    private HashMap<String, Region> regionsByName;

    public Game createGame() {
        regionsByName = new HashMap<>();

        var regions = constructRegions();
        var landmarkTiles = constructLandmarkTiles();

        SauronPlayer sauronPlayer = new SauronPlayer(
                InitialConfig.SauronPlayer.Coins,
                InitialConfig.SauronPlayer.Units,
                InitialConfig.SauronPlayer.Towers);

        FellowshipPlayer fellowshipPlayer = new FellowshipPlayer(
                InitialConfig.FellowshipPlayer.Coins,
                InitialConfig.FellowshipPlayer.Units,
                InitialConfig.FellowshipPlayer.Towers);

        ArrayList<RoundInformation> roundInformations = new ArrayList<>();
        for(RoundConfig roundConfig : Rounds) {
            roundInformations.add(new RoundInformation( roundConfig.CoinsPerChapterCardDiscard));
        }

        var context = new GameContext.Builder()
            .addRegions(regions)
            .addLandmarkTiles(landmarkTiles)
            .withPlayers(fellowshipPlayer, sauronPlayer);
          //  .build();


        var state = constructGameState(sauronPlayer, fellowshipPlayer);

        return new Game(context.build(), state);
    }

    private GameState constructGameState(SauronPlayer sauronPlayer, FellowshipPlayer fellowshipPlayer) {
        Player startingPlayer;
        Player otherPlayer;

        if(StartingPlayer.equals("Sauron")) {
            startingPlayer =sauronPlayer;
            otherPlayer = fellowshipPlayer;
        } else if(StartingPlayer.equals("Fellowship")) {
            startingPlayer = fellowshipPlayer;
            otherPlayer = sauronPlayer;
        } else {
            throw new IllegalArgumentException("Invalid starting player: " + StartingPlayer);
        }


        var state = new GameState(startingPlayer, otherPlayer);

        return state;
    }

    // TODO: Parse actions
    private ArrayList<LandmarkTile> constructLandmarkTiles() {
        ArrayList<LandmarkTile> landmarkTiles = new ArrayList<>();
        for(var config : LandmarkTilesToUse) {
            List<IAction> actions = config.Effects
                    .stream()
                    .map(e -> e.constructAction(regionsByName))
                    .toList();

            landmarkTiles.add(new LandmarkTile(regionsByName.get(config.FortressToPlaceTo),
                    constructSkillSet(config.RequiredSkills), actions));
        }

        return landmarkTiles;
    }

    private SkillSet constructSkillSet(SkillsetConfig config) {
        // This must be done according to the Skill enum order definition
        return new SkillSet(new int[]{ config.Ruse, config.Strength,
                config.Courage, config.Knowledge, config.Leadership}, config.IsOptional);
    }


    private ArrayList<Region> constructRegions() {
        ArrayList<Region> regions = new ArrayList<>();

        for(RegionConfig config : Regions) {
            Region newRegion = new Region(RegionType.valueOf(config.Name), config.Fortress);
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
