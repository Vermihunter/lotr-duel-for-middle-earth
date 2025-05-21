package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;

public class ChooseMoveHandler extends CommandHandler {


    public ChooseMoveHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        return null;
    }
}