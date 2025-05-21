package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.state_serializers.FullMapStateSerializer;

import java.util.ResourceBundle;

public class PrintGameStateHandler extends CommandHandler {
    protected final Game game;

    public PrintGameStateHandler(Context context, String name, String description, Game game) {
        super(context, name, description);
        this.game = game;
    }

    public static void addAsSubHandler(ResourceBundle resourceBundle, Game game, ConsoleView console, Context context) {
        var commandName = resourceBundle.getString(CommandResourceBundleKeys.DISPLAY_GAME_STATE_NAME);
        var commandDescription = resourceBundle.getString(CommandResourceBundleKeys.DISPLAY_GAME_STATE_HELP_MESSAGE);
        console.getBaseCommandHandler().registerSubCommand(commandName, new PrintGameStateHandler(context, commandName, commandDescription, game));
    }

    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        context.out.println(FullMapStateSerializer.serialize(game));

        return new CommandResult(CommandResultType.CONTINUE, null);
    }


}
