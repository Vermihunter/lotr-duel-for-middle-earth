package vermesa.lotr;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import vermesa.lotr.model.game.Game;

import java.lang.reflect.Constructor;
import java.util.EnumMap;
import java.util.Set;

public class TestedGameStateFactory {

    private static final EnumMap<TestedGameState, TestedGameStateCreator> gameStateCreators;

    static {
        gameStateCreators = new EnumMap<>(TestedGameState.class);

        Reflections reflections = new Reflections("vermesa.lotr", Scanners.TypesAnnotated);

        // 2. Find all classes annotated with @TestedGameStateCreatorInfo
        Set<Class<?>> creatorClasses =
                reflections.getTypesAnnotatedWith(TestedGameStateCreatorInfo.class);

        for (Class<?> creatorClass : creatorClasses) {
            // 3. Read the annotation
            TestedGameStateCreatorInfo info = creatorClass.getAnnotation(TestedGameStateCreatorInfo.class);
            TestedGameState state = info.state();

            try {
                // 4. Instantiate via default (no-arg) constructor
                Constructor<?> ctor = creatorClass.getDeclaredConstructor();
                ctor.setAccessible(true); // in case it's not public
                Object instance = ctor.newInstance();

                gameStateCreators.put(state, (TestedGameStateCreator) instance);
                // 5. If you have a common interface, cast and use it:
                // GameStateCreator<?> creator = (GameStateCreator<?>) instance;
                // creator.createInitialState();

            } catch (Exception e) {
                System.err.printf(
                        "Failed to instantiate %s: %s%n",
                        creatorClass.getName(), e.getMessage()
                );
            }
        }
    }

    public static Game createGame(TestedGameState state) {
        var gameCreator = gameStateCreators.get(state);
        if (gameCreator == null) {
            throw new IllegalStateException("No creator for game state " + state);
        }

        return gameCreator.createGame();
    }

}
