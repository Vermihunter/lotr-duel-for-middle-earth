package vermesa.lotr.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = DefaultGameBuilder.buildDefaultGame(new Random(1));
    }

    @Test
    void getPossibleMoves() {
    }

    @Test
    void makeMove() {

    }
}