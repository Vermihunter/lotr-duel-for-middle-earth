package vermesa.lotr.serialization;

import vermesa.lotr.model.game.Game;

import java.util.Random;

/**
 * Basic general interface for external configurations
 */
public interface IGameConfig {
    /**
     * Creates a game from a configuration
     *
     * @param rand A random generator that is present in the context e.g. for shuffling cards for randomness
     * @return A Game object that can be played
     */
    Game createGame(Random rand);
}
