package vermesa.lotr.view.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.serialization.json.JsonConfig;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.commands.CommandViewRegistry;
import vermesa.lotr.view.console.commands.handlers.*;
import vermesa.lotr.view.console.game_events.EnemyMoveMadeGameEvent;
import vermesa.lotr.view.console.game_events.GameEndedEvent;
import vermesa.lotr.view.console.game_events.GameEvent;
import vermesa.lotr.view.console.game_events.QuitGameEvent;
import vermesa.lotr.view.console.move_serializers.ActionSerializerRegistry;
import vermesa.lotr.view.console.utils.BoxPrinter;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {


    public static void main(String[] args) throws IOException {
        // Construct config from JSON
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream in = Main.class.getResourceAsStream("/DefaultConfig.json");

        JsonConfig config = objectMapper.readValue(in, JsonConfig.class);

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

                if (event instanceof GameEndedEvent) {
                    ctx.out.print("\b\b");
                    BoxPrinter.printBox(((GameEndedEvent) event).finalState().toString(), 20, ctx);
                    break;
                }

                if (event instanceof EnemyMoveMadeGameEvent) {

                    var moves = ((EnemyMoveMadeGameEvent) event).moves();

                    for (var move : moves) {
                        var moveSerializer = ActionSerializerRegistry.getAll().get(move.getClass());
                        String moveSerialized = (moveSerializer == null)
                                ? move.toString()
                                : moveSerializer.serialize(move);

                        ctx.out.print("\b>> Enemy player has made move: " + moveSerialized);
                    }

                    if (((EnemyMoveMadeGameEvent) event).humanPlayersTurn()) {
                        view.printHelp();
                    }

                    ctx.out.print("> ");
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

                view.switchToView(commandResult.nextView());
                if (commandResult.printHelp()) {
                    try {
                        view.printHelp();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        inputThread.setDaemon(true);
        inputThread.start();
    }

    private static ConsoleView constructStartingView(Context ctx) {
        ResourceBundle resourceBundle = ctx.resourceBundle;
        CommandViewRegistry viewRegistry = new CommandViewRegistry(ctx, "vermesa.lotr.view.console.commands.handlers", resourceBundle);

        return new ConsoleView(viewRegistry, ctx, AppState.MAIN);
    }
}
