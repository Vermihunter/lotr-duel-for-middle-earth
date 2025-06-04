package vermesa.lotr.model.player;

import java.io.Serializable;

/**
 * Sauron player of the game
 * Note that in every game there is a single SauronPlayer
 */
public class SauronPlayer extends Player implements Serializable {
    public static final String NAME = "Sauron";

    public SauronPlayer(int startingCoins, int units, int towers) {
        super(startingCoins, units, towers);
    }

    @Override
    public boolean isFellowshipPlayer() {
        return false;
    }

    @Override
    public boolean isSauronPlayer() {
        return true;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}
