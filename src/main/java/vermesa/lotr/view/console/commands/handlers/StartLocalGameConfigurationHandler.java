package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.ai_players.IAIPlayerConfig;
import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.*;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.handlers.ai_player_constructor.AIPlayerConstructor;

import java.time.Duration;
import java.util.Random;


@CommandInfo(
        nameKey = CommandResourceBundleKeys.START_NAME,
        descKey = CommandResourceBundleKeys.START_HELP_MESSAGE,
        states = {AppState.MAIN}
)
public class StartLocalGameConfigurationHandler extends StartGameConfigurationHandler {

    public StartLocalGameConfigurationHandler(Context context, String name, String description) {
        super(context, name, description);
    }


    @Override
    protected IOpponentController getOpponentController() {
        // Start game configuration
        context.out.println(">> ");
        context.out.println(">> Game configuration has been started.");
        Random rand = new Random(113);

        // Create game
        var game = context.gameConfig.createGame(rand);
        // Get user choices
        IAIPlayerConfig aiPlayerConfig = chooseAIPlayer(rand);
        Player humanPlayerType = choosePlayerType(game);
        Player opponentPlayerType = (humanPlayerType == game.context().getFellowshipPlayer())
                ? game.context().getSauronPlayer()
                : game.context().getFellowshipPlayer();

        IAIPlayer aiPlayer = aiPlayerConfig.constructAIPlayer(opponentPlayerType);

        var opponentControllerContext = new OpponentControllerContext(game, humanPlayerType, opponentPlayerType);
        var opponentController = new OpponentAIController(aiPlayer, opponentControllerContext);
        var controllerLock = opponentController.getAiWorkerLock();

        Thread aiPlayerThread = getControllerThread(humanPlayerType, aiPlayer, opponentControllerContext, controllerLock);
        aiPlayerThread.start();

        /*
        if(game.state().getPlayerOnMove().equals(opponentPlayerType)) {
            synchronized (controllerLock) {
                controllerLock.notify();
            }
        }
        */

        return opponentController;
    }

    private Thread getControllerThread(Player humanPlayerType, IAIPlayer aiPlayer, OpponentControllerContext opponentControllerContext, Object controllerLock) {


        var controller = new AIPlayerController(
                aiPlayer,
                opponentControllerContext,
                controllerLock,
                context.enemyMoveMadeListener,
                context.gameHasEndedListener,
                humanPlayerType,
                Duration.ofMillis(20) // Minimum time to wait for opponent move -→ 2000
        );

        Thread controllerThread = new Thread(controller::run);
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
            if (type.equals(PlayerType.Sauron.name())) {
                return game.context().getSauronPlayer();
            }

            if (type.equals(PlayerType.Fellowship.name())) {
                return game.context().getFellowshipPlayer();
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
