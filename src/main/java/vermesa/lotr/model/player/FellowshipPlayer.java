package vermesa.lotr.model.player;

public class FellowshipPlayer extends Player {
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
