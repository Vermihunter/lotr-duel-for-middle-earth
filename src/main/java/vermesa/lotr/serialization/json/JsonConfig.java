package vermesa.lotr.serialization.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vermesa.lotr.model.Game;
import vermesa.lotr.model.GameContext;
import vermesa.lotr.serialization.IGameConfig;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonConfig implements IGameConfig {
    public InitialConfig InitialConfig;
    public String StartingPlayer;
    public int TotalCoinCount;
    public List<RoundConfig> Rounds;
    public List<LandmarkTileConfig> LandmarkTilesToUse;

    public Game createGame() {
        var builder = new GameContext.Builder();

        return null;
    }
}
