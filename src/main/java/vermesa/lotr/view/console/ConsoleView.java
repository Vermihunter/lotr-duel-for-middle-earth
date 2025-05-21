package vermesa.lotr.view.console;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.commands.exceptions.BadCommandArgumentsException;
import vermesa.lotr.view.console.commands.exceptions.CommandNotFoundException;
import vermesa.lotr.view.console.commands.handlers.CommandHandler;
import vermesa.lotr.view.console.commands.CommandResult;

public class ConsoleView {
    private final CommandHandler baseCommandHandler;
    private final Context context;

    public ConsoleView(CommandHandler baseCommandHandler, Context context) {
        this.baseCommandHandler = baseCommandHandler;
        this.context = context;
    }

    public CommandHandler getBaseCommandHandler() {
        return baseCommandHandler;
    }

    public CommandResult execute(String[] command) {
        return handleCommand(command);
    }

    private CommandResult handleCommand(String[] command) {
        CommandResult result;

        try {
            result = baseCommandHandler.handleCommand(command, this);
        } catch (CommandNotFoundException e) {
            printHelp();
            result = new CommandResult(CommandResultType.ERROR, e.getMessage());
        } catch (BadCommandArgumentsException e) {
            result = new CommandResult(CommandResultType.ERROR, e.getMessage());
        }

        return result;
    }

    public void printHelp() {
        baseCommandHandler.handleCommand(new String[]{context.resourceBundle.getString(CommandResourceBundleKeys.LIST_NAME)}, this);
    }
}
