package vermesa.lotr.model;

import vermesa.lotr.model.chapter_cards.ChapterCardContext;
import vermesa.lotr.model.chapter_cards.RoundChapterCardConfig;
import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.model.central_board.Region;

import java.util.ArrayList;

public class GameContext {
    ArrayList<LandmarkTile> landmarkTiles;
    // 1 for each player
    ChapterCardContext[] chapterCardContexts;
    ArrayList<RoundInformation> roundInformations;
    FellowshipPlayer fellowshipPlayer;
    SauronPlayer sauronPlayer;
    ArrayList<Region> regions;

    private GameContext() {}

    public static class Builder {
        ArrayList<Region> regions;
        ArrayList<LandmarkTile> landmarkTiles;
        ArrayList<RoundInformation> roundInformations;
        FellowshipPlayer fellowshipPlayer;
        SauronPlayer sauronPlayer;

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

        public Builder withRoundInformations(ArrayList<RoundInformation> roundInformations) {
            this.roundInformations = roundInformations;
            return this;
        }

        public GameContext build() {
            GameContext gameContext = new GameContext();


            return gameContext;
        }
    }

}
