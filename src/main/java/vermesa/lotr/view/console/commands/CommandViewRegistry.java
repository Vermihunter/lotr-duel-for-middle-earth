package vermesa.lotr.view.console.commands;

import org.reflections.Reflections;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.handlers.CommandHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class CommandViewRegistry {
    private final HashMap<AppState, CommandView> commandViews = new HashMap<>();

    public CommandViewRegistry(Context context, String basePackage, ResourceBundle bundle) {
        for (AppState appState : AppState.values()) {
            commandViews.put(appState, new CommandView(context));
        }

        Reflections reflections = new Reflections(basePackage);

        // Find all classes that extend CommandHandler
        Set<Class<? extends CommandHandler>> handlerClasses = reflections.getSubTypesOf(CommandHandler.class);

        for (Class<? extends CommandHandler> cls : handlerClasses) {
            // Only process classes annotated with @CommandInfo
            CommandInfo info = cls.getAnnotation(CommandInfo.class);
            if (info == null) continue;

            String nameKey = info.nameKey();
            String descKey = info.descKey();
            AppState[] states = info.states();
            boolean isGlobal = info.global();

            String displayName = bundle.getString(nameKey);
            String description = bundle.getString(descKey);

            // Instantiate exactly one instance of this handler
            CommandHandler handlerInstance;
            try {
                handlerInstance = cls.getDeclaredConstructor(Context.class, String.class, String.class).newInstance(context, displayName, description);
            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException ex) {
                // If instantiation fails, just skip this handler
                continue;
            }

            AppState[] handlerStates = (isGlobal) ? AppState.values() : states;
            addToStates(handlerStates, handlerInstance);
        }
    }

    private void addToStates(AppState[] states, CommandHandler handler) {
        for (AppState state : states) {
            commandViews.get(state).registerCommandHandler(handler);
        }
    }

    public CommandView getCommandView(AppState appState) {
        return commandViews.get(appState);
    }
}
