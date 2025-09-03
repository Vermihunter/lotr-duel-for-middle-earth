package vermesa.lotr.model.game;

import java.util.Random;

public class DefaultGameBuilder {

    public static Game buildDefaultGame(Random rand) {

        var context = DefaultGameContextBuilder.buildDefaultGameContext(rand, DefaultGameContextBuilder.buildDefaultBuilderConfig());
        var state = DefaultGameStateBuilder.buildDefaultGameState(rand, context);
        return new Game(context, state);
    }
}
