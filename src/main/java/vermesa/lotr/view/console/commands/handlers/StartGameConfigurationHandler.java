package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.HumanPlayerController;
import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.event_handlers.ControllerEnemyMoveMadeListener;
import vermesa.lotr.view.console.event_handlers.GameHasEndedListener;

import java.rmi.RemoteException;
import java.time.Duration;
import java.util.ResourceBundle;

public abstract class StartGameConfigurationHandler extends CommandHandler {

    public StartGameConfigurationHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) throws RemoteException {
        //HandlersRegistry registry = context.registry;
        ResourceBundle resourceBundle = context.resourceBundle;

        // Unregister "start" command -> or not - start a new configuration
        console.getBaseCommandHandler().unregisterSubCommand(resourceBundle.getString(CommandResourceBundleKeys.START_NAME));

        IOpponentController opponentController = getOpponentController();
        context.controller = opponentController;

        Game game = opponentController.getGame();
        // Start controller in the background

        //Thread controllerThread = getControllerThread(opponentController.getHumanPlayer(), game, opponentController);
        //controllerThread.start();

        // Add this back you want to add adding+removing the list_moves command when the enemy player is moving
        /*
        boolean isHumanPlayerOnMove = game.getState().getPlayerOnMove() == humanPlayerType;
        if (isHumanPlayerOnMove) {
            // ...
        }
        */

        ListAvailableMovesHandler.addAsSubHandler(resourceBundle, game, console, context);
        PrintGameStateHandler.addAsSubHandler(resourceBundle, game, console, context);

        //context.notifyController();
        /*
        synchronized (context.controllerLock) {
            context.controllerLock.notifyAll();
        }
        */
        return CommandResult.OK;
    }

    /*
    private Thread getControllerThread(Player humanPlayerType, Game game, IOpponentController opponentController) {

        var listener = new ControllerEnemyMoveMadeListener(context.eventQueue);
        var gameHasEndedListener = new GameHasEndedListener(context.eventQueue);

        var controller = new HumanPlayerController(
                opponentController, // Controller for the enemy → AI / Network / ...
                gameHasEndedListener,
                humanPlayerType, // Sauron/Fellowship player for human player
                game, // Game that will be played
                listener, // Event listener for enemy move making
                Duration.ofMillis(20), // Minimum time to wait for opponent move -→ 2000
                context.controllerLock
        );


        context.controller = controller;

        Thread controllerThread = new Thread(controller::start);
        controllerThread.setDaemon(true);
        return controllerThread;


    }
    */


    abstract protected IOpponentController getOpponentController();
}
