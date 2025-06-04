package vermesa.lotr.view.console.commands;

import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.handlers.CommandHandler;

import java.rmi.RemoteException;

public class CommandView {
    private final CommandHandler baseHandler;

    public CommandView(Context context) {
        baseHandler = new CommandHandler(context, null, null);
    }

    public void registerCommandHandler(CommandHandler handler) {
        baseHandler.registerSubCommand(handler.getName(), handler);
    }

    public CommandHandler getCommandHandler() {
        return baseHandler;
    }

    public CommandResult handle(String[] command, ConsoleView console) throws RemoteException {
        return baseHandler.handleCommand(command, console);
    }

    public void printHelp() {
        baseHandler.printHelp();
    }
}
