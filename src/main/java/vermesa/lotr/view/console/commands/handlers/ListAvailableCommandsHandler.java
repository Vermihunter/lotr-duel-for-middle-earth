package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.ConsoleView;

public class ListAvailableCommandsHandler extends CommandHandler {

    public ListAvailableCommandsHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        var baseCommandHandler = console.getBaseCommandHandler();

        context.out.println(">> Available commands: ");

        baseCommandHandler.subcommandHandlers.forEach((commandName, handler) -> {
            context.out.println(">>\t" + commandName + "\t\t- " + handler.getDescription());
        });


        return CommandResult.OK;
    }
}
