package vermesa.lotr.serialization;

import vermesa.lotr.model.game.Game;

import java.util.Random;

public interface IGameConfig {
    Game createGame(Random rand);
}
