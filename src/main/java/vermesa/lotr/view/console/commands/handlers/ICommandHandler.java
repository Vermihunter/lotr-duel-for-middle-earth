package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.ConsoleView;

public interface ICommandHandler {
    void registerSubCommand(String subCommandName, ICommandHandler handler);

    void unregisterSubCommand(String subCommandName);

    CommandResult handleCommand(String[] commandParts, ConsoleView console);

    String getDescription();

    String getName();
}
