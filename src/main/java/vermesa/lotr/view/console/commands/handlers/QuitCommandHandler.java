package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.exceptions.BadCommandArgumentsException;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.ConsoleView;

@CommandInfo(
        nameKey = CommandResourceBundleKeys.QUIT_NAME,
        descKey = CommandResourceBundleKeys.QUIT_HELP_MESSAGE,
        global = true
)
public class QuitCommandHandler extends CommandHandler {
    private static final CommandResult quitCommandResult = new CommandResult(CommandResultType.QUIT, null, false, null);

    public QuitCommandHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        if (commandParts.length != 0) {
            throw new BadCommandArgumentsException("Quit command accepts no arguments");
        }

        return quitCommandResult;
    }
}
