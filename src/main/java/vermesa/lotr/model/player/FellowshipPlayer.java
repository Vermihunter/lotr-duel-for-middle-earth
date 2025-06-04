package vermesa.lotr.model.player;

import java.io.Serializable;

/**
 * Fellowship player of the game
 * Note that in every game there is a single FellowshipPlayer
 */
public class FellowshipPlayer extends Player implements Serializable {
    public static final String NAME = "Fellowship";

    public FellowshipPlayer(int startingCoins, int units, int towers) {
        super(startingCoins, units, towers);
    }

    @Override
    public boolean isFellowshipPlayer() {
        return true;
    }

    @Override
    public boolean isSauronPlayer() {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
