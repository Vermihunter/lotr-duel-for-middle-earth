package vermesa.lotr.view.console.commands.handlers.game_state_handlers;

import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.ConsoleView;

public class GameStatePrinterHandler extends GameStateHandler {

    public GameStatePrinterHandler(Context context, String name, String description, Game game) {
        super(context, name, description, game);
    }

    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        return null;
    }
}
