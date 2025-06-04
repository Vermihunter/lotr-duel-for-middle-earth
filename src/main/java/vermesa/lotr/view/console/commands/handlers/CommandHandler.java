package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.exceptions.CommandNotFoundException;
import vermesa.lotr.view.console.ConsoleView;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler implements ICommandHandler {
    protected final Context context;
    protected final String name;
    private final String description;
    protected HashMap<String, ICommandHandler> subcommandHandlers = new HashMap<>();

    public CommandHandler(Context context, String name, String description) {
        this.context = context;
        this.name = name;
        this.description = description;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    public final void registerSubCommand(String name, ICommandHandler handler) {
        subcommandHandlers.put(name, handler);
    }

    public final void unregisterSubCommand(String name) {
        subcommandHandlers.remove(name);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) throws RemoteException {
        var subcommand = subcommandHandlers.get(commandParts[0]);
        if (subcommand == null) {
            throw new CommandNotFoundException("No such subcommand: " + commandParts[0]);
        }

        return subcommand.handleCommand(Arrays.copyOfRange(commandParts, 1, commandParts.length), console);
    }

}
