package vermesa.lotr.view.console.commands.handlers;

import java.util.HashMap;

public class HandlersRegistry {
    private final HashMap<String, ICommandHandler> handlers = new HashMap<>();

    public void register(String name, ICommandHandler handler) {
        handlers.put(name, handler);
    }

    public void unregister(String name) {
        handlers.remove(name);
    }

    public ICommandHandler getHandler(String name) {
        return handlers.get(name);
    }

}
