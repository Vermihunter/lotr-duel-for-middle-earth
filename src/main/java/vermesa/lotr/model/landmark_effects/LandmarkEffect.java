package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.player.Player;

public abstract class LandmarkEffect {

    public abstract void execute();

    protected void placeFortressTo(RegionType region, Player player /*, Game game */) {

    }
}
