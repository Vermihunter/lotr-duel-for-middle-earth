package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.ConsoleView;

@CommandInfo(
        nameKey = CommandResourceBundleKeys.LIST_NAME,
        descKey = CommandResourceBundleKeys.LIST_HELP_MESSAGE,
        global = true
)
public class ListAvailableCommandsHandler extends CommandHandler {

    public ListAvailableCommandsHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        var baseCommandHandler = console.getBaseCommandHandler();
        baseCommandHandler.printHelp();


        return new CommandResult(CommandResultType.CONTINUE, null, false, console.getCurrentAppState());
    }
}
