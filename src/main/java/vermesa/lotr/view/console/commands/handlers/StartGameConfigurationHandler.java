package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;

import java.rmi.RemoteException;

public abstract class StartGameConfigurationHandler extends CommandHandler {

    public StartGameConfigurationHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) throws RemoteException {
        context.controller = getOpponentController();

        return new CommandResult(CommandResultType.CONTINUE, null, true, AppState.GAME_PLAY);
    }

    abstract protected IOpponentController getOpponentController();
}
