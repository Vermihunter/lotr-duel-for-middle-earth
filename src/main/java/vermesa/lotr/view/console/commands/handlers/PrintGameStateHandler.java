package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.state_serializers.FullMapStateSerializer;

import java.rmi.RemoteException;
import java.util.ResourceBundle;

@CommandInfo(
        nameKey = CommandResourceBundleKeys.DISPLAY_GAME_STATE_NAME,
        descKey = CommandResourceBundleKeys.DISPLAY_GAME_STATE_HELP_MESSAGE,
        states = {AppState.GAME_PLAY}
)
public class PrintGameStateHandler extends CommandHandler {

    public PrintGameStateHandler(Context context, String name, String description) {
        super(context, name, description);
    }


    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {

        try {
            context.out.println(FullMapStateSerializer.serialize(context.controller.getGame()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        return CommandResult.OK(console);
    }


}
