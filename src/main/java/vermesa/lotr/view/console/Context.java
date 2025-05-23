package vermesa.lotr.view.console;

import vermesa.lotr.controllers.HumanPlayerController;
import vermesa.lotr.controllers.LotrController;
import vermesa.lotr.serialization.IGameConfig;
import vermesa.lotr.view.console.commands.handlers.HandlersRegistry;
import vermesa.lotr.view.console.game_events.GameEvent;

import java.io.PrintStream;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Context {
    public final ResourceBundle resourceBundle;
    public final HandlersRegistry registry;
    public final PrintStream out;
    public final PrintStream err;
    public final Scanner scanner;
    public final IGameConfig gameConfig;
    public final BlockingQueue<GameEvent> eventQueue;
    public final Object controllerLock = new Object();
    public HumanPlayerController controller;

    public Context(ResourceBundle resourceBundle, HandlersRegistry registry, PrintStream out, PrintStream err, Scanner scanner, IGameConfig gameConfig, BlockingQueue<GameEvent> eventQueue) {
        this.resourceBundle = resourceBundle;
        this.registry = registry;
        this.out = out;
        this.err = err;
        this.scanner = scanner;
        this.gameConfig = gameConfig;
        this.eventQueue = eventQueue;
    }
}
