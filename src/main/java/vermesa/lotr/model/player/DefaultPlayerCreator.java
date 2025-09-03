package vermesa.lotr.model.player;

public class DefaultPlayerCreator {

    public static Player createFellowshipPlayer() {
        return new Player(PlayerType.Fellowship, 3, 15, 7);
    }

    public static Player createSauronPlayer() {
        return new Player(PlayerType.Sauron, 2, 15, 7);
    }

}
