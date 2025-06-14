package vermesa.lotr.model.player;

public enum PlayerType {
    Sauron,
    Fellowship;

    public Player toPlayer(int startingCoins, int units, int towers) {
        return new Player(this, startingCoins, units, towers);
    }
}
