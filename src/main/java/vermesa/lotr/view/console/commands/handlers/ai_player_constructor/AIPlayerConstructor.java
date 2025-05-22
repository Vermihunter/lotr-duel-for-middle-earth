package vermesa.lotr.view.console.commands.handlers.ai_player_constructor;

import org.reflections.Reflections;
import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.ai_players.IAIPlayerConfig;

import java.util.*;

public class AIPlayerConstructor {
    private final static Map<String, IAIPlayerConstructor> constructors = new HashMap<>();

    static {

        String basePackage = "vermesa.lotr.view.console.commands.handlers.ai_player_constructor";
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends IAIPlayerConstructor>> impls = reflections.getSubTypesOf(IAIPlayerConstructor.class);
        for (Class<? extends IAIPlayerConstructor> cls : impls) {
            try {
                // assumes a public no-arg constructor
                IAIPlayerConstructor player = cls.getDeclaredConstructor().newInstance();
                constructors.put(player.getName(), player);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to instantiate " + cls, e);
            }
        }


    }

    public static IAIPlayerConfig constructPlayer(String[] args, Random rand) {
        // Bad call → no arguments passed
        if (args.length == 0) {
            return null;
        }

        String playerName = args[0];
        IAIPlayerConstructor playerConstructor = constructors.get(playerName);

        // No AI Player with given name
        if (playerConstructor == null) {
            return null;
        }

        // Other error handling is passed to the concrete AI player type constructor
        return playerConstructor.constructPlayerConfig(Arrays.copyOfRange(args, 1, args.length), rand);
    }

    public static Set<String> getPlayerNames() {
        return constructors.keySet();
    }
}
