package vermesa.lotr.model.player;

public class SauronPlayer extends Player {
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
