package vermesa.lotr.model;

import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.SauronPlayer;

import java.util.ArrayList;

public class GameContext {
    ArrayList<LandmarkTile> landmarkTiles;
    RoundInformation currRoundInformation;
    FellowshipPlayer fellowshipPlayer;
    SauronPlayer sauronPlayer;

    private GameContext() {}

    public static class Builder {

        public GameContext build() {
            GameContext gameContext = new GameContext();


            return gameContext;
        }
    }

}
