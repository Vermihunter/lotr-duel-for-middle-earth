package vermesa.lotr.view.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.serialization.json.JsonConfig;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.commands.handlers.*;
import vermesa.lotr.view.console.game_events.EnemyMoveMadeGameEvent;
import vermesa.lotr.view.console.game_events.GameEvent;
import vermesa.lotr.view.console.game_events.QuitGameEvent;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    /*
    String localeTag = Config.get("default.locale");
    String welcome = Config.get("welcome.message");
    System.out.println("Welcome message: " + welcome);
    System.out.println("Default locale: " + localeTag);
    */

    public static void main(String[] args) throws IOException {
        // Construct config from JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonConfig config = objectMapper.readValue(new File("DefaultConfig.json"), JsonConfig.class);

        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                CommandResourceBundleKeys.BUNDLE_NAME,
                Locale.ENGLISH);

        BlockingQueue<GameEvent> eventQueue = new LinkedBlockingQueue<>();
        HandlersRegistry registry = new HandlersRegistry();
        Scanner scanner = new Scanner(System.in);

        Context ctx = new Context(resourceBundle, registry, System.out, System.err, scanner, config, eventQueue);

        ConsoleView view = constructStartingView(ctx);
        view.printWelcome();
        view.printHelp();

        runInputThread(eventQueue, ctx, view);

        while (true) {
            try {
                GameEvent event = eventQueue.take();

                if (event instanceof QuitGameEvent) {
                    break;
                }

                if (event instanceof EnemyMoveMadeGameEvent) {

                    var moves = ((EnemyMoveMadeGameEvent) event).move();

                    for (var move : moves) {
                        var moveSerializer = ActionSerializerRegistry.getAll().get(move.getClass());
                        String moveSerialized = (moveSerializer == null)
                                ? move.toString()
                                : moveSerializer.serialize(move);

                        ctx.out.println("\b>> Enemy player has made move: " + moveSerialized);
                        view.printHelp();
                        ctx.out.print("> ");
                    }

                    synchronized (ctx.controllerLock) {
                        ctx.controllerLock.notify();
                    }
                }

            } catch (InterruptedException ignored) {

            }
        }
    }

    private static void runInputThread(BlockingQueue<GameEvent> eventQueue, Context ctx, ConsoleView view) {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!Thread.currentThread().isInterrupted()) {
                ctx.out.print("> ");
                var commands = scanner.nextLine().strip().split("\\s+");
                var commandResult = view.execute(commands);

                if (commandResult.commandResult() == CommandResultType.QUIT) {
                    eventQueue.add(new QuitGameEvent());
                    break;
                }

                if (commandResult.message() != null) {
                    ctx.out.println(">>> " + commandResult.message());
                }
                if (commandResult.printHelp()) {
                    view.printHelp();
                }
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();
    }

    private static ConsoleView constructStartingView(Context ctx) {
        CommandHandler baseHandler = new CommandHandler(ctx, null, null);
        ResourceBundle resourceBundle = ctx.resourceBundle;
        HandlersRegistry registry = ctx.registry;

        // List all commands command
        var listHandler = new ListAvailableCommandsHandler(
                ctx,
                resourceBundle.getString(CommandResourceBundleKeys.LIST_NAME),
                resourceBundle.getString(CommandResourceBundleKeys.LIST_HELP_MESSAGE));
        registry.register(listHandler.getName(), listHandler);
        baseHandler.registerSubCommand(listHandler.getName(), listHandler);

        // Start game command
        var startHandler = new StartGameConfigurationHandler(
                ctx,
                resourceBundle.getString(CommandResourceBundleKeys.START_NAME),
                resourceBundle.getString(CommandResourceBundleKeys.START_HELP_MESSAGE));
        registry.register(startHandler.getName(), startHandler);
        baseHandler.registerSubCommand(startHandler.getName(), startHandler);

        // Quit application command
        var quitHandler = new QuitCommandHandler(
                ctx,
                resourceBundle.getString(CommandResourceBundleKeys.QUIT_NAME),
                resourceBundle.getString(CommandResourceBundleKeys.QUIT_HELP_MESSAGE));
        registry.register(quitHandler.getName(), quitHandler);
        baseHandler.registerSubCommand(quitHandler.getName(), quitHandler);

        return new ConsoleView(baseHandler, ctx);
    }
}
