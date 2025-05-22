package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.ai_players.IAIPlayerConfig;
import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.HumanPlayerController;
import vermesa.lotr.controllers.OpponentAIController;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.ControllerEnemyMoveMadeListener;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.commands.handlers.ai_player_constructor.AIPlayerConstructor;
import vermesa.lotr.view.console.ConsoleView;

import java.time.Duration;
import java.util.Random;
import java.util.ResourceBundle;

public class StartGameConfigurationHandler extends CommandHandler {

    public StartGameConfigurationHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        HandlersRegistry registry = context.registry;
        ResourceBundle resourceBundle = context.resourceBundle;

        // Unregister "start" command -> or not - start a new configuration
        console.getBaseCommandHandler().unregisterSubCommand(resourceBundle.getString(CommandResourceBundleKeys.START_NAME));

        // Start game configuration
        context.out.println(">> ");
        context.out.println(">> Game configuration has been started.");
        Random rand = new Random(111);

        // Create game
        var game = context.gameConfig.createGame(rand);
        // Get user choices
        IAIPlayerConfig aiPlayerConfig = chooseAIPlayer(rand);
        Player humanPlayerType = choosePlayerType(game);
        Player opponentPlayerType = (humanPlayerType == game.getContext().getFellowshipPlayer())
                ? game.getContext().getSauronPlayer()
                : game.getContext().getFellowshipPlayer();

        IAIPlayer aiPlayer = aiPlayerConfig.constructAIPlayer(opponentPlayerType);

        boolean isHumanPlayerOnMove = game.getState().getPlayerOnMove() == humanPlayerType;

        // Start controller in the background
        Thread controllerThread = getControllerThread(aiPlayer, humanPlayerType, game);
        controllerThread.start();

        // Add this back you want to add adding+removing the list_moves command when the enemy player is moving
        /*
        if (isHumanPlayerOnMove) {
        }
        */

        ListAvailableMovesHandler.addAsSubHandler(resourceBundle, game, console, context);
        PrintGameStateHandler.addAsSubHandler(resourceBundle, game, console, context);

        synchronized (context.controllerLock) {
            context.controllerLock.notifyAll();
        }

        return CommandResult.OK;
    }

    private Thread getControllerThread(IAIPlayer aiPlayer, Player humanPlayerType, Game game) {
        var opponentController = new OpponentAIController(aiPlayer);
        var listener = new ControllerEnemyMoveMadeListener(context.eventQueue);
        var controller = new HumanPlayerController(
                opponentController, // Controller for the enemy → AI / Network / ...
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

    /**
     * Choose whether the player wants to play as Sauron or Fellowship
     *
     * @return Sauron or Fellowship
     */
    private Player choosePlayerType(Game game) {
        context.out.println(">> ");
        context.out.println(">> Please enter a player type to play with.");
        context.out.println(">> The available player types are:");
        context.out.println(">> \t(1) Sauron");
        context.out.println(">> \t(2) Fellowship");

        while (true) {
            context.out.print(">> Player type to play with: ");
            String input = context.scanner.nextLine();
            String[] elements = input.split("\\s+");
            context.out.println(">> ");

            if (elements.length > 1) {
                continue;
            }

            String type = elements[0];
            if (type.equals(SauronPlayer.NAME)) {
                return game.getContext().getSauronPlayer();
            }

            if (type.equals(FellowshipPlayer.NAME)) {
                return game.getContext().getFellowshipPlayer();
            }


            context.out.println(">>> Wrong player type! Please choose a valid player type to play with.");
        }
    }

    private IAIPlayerConfig chooseAIPlayer(Random rand) {
        context.out.println(">> Please choose an AI player to play against.");
        context.out.println(">> The available AI players are:");

        int index = 1;
        var AIPlayerTypes = AIPlayerConstructor.getPlayerNames();
        for (var AI_playerName : AIPlayerTypes) {
            context.out.println(">> \t(" + index + ") " + AI_playerName);
            index++;
        }

        while (true) {
            context.out.print(">> Enemy AI player to play against: ");
            String input = context.scanner.nextLine();

            var aiPlayerEntry = AIPlayerConstructor.constructPlayer(input.split("\\s+"), rand);
            if (aiPlayerEntry != null) {
                return aiPlayerEntry;
            }

            context.out.println(">>> Invalid AI player name.");
        }

    }
}
