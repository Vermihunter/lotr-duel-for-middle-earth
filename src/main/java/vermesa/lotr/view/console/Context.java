package vermesa.lotr.view.console;

import vermesa.lotr.controllers.*;
import vermesa.lotr.serialization.IGameConfig;
import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.view.console.commands.handlers.HandlersRegistry;
import vermesa.lotr.view.console.event_handlers.ControllerEnemyMoveMadeListener;
import vermesa.lotr.view.console.event_handlers.GameHasEndedListener;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;
import vermesa.lotr.view.console.game_events.GameEvent;

import java.io.PrintStream;
import java.rmi.RemoteException;
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
    public final IEnemyMoveMadeListener enemyMoveMadeListener;
    public final IGameHasEndedListener gameHasEndedListener;
    public IOpponentController controller;
    public LobbyEventListener lobbyClientEventListener;
    public NetworkEnemyMoveMadeListener enemyNetworkMoveMadeListener;


    public Context(ResourceBundle resourceBundle, HandlersRegistry registry, PrintStream out, PrintStream err, Scanner scanner, IGameConfig gameConfig, BlockingQueue<GameEvent> eventQueue) throws RemoteException {
        this.resourceBundle = resourceBundle;
        this.registry = registry;
        this.out = out;
        this.err = err;
        this.scanner = scanner;
        this.gameConfig = gameConfig;
        this.eventQueue = eventQueue;

        this.enemyMoveMadeListener = new ControllerEnemyMoveMadeListener(eventQueue);
        this.gameHasEndedListener = new GameHasEndedListener(eventQueue);
    }

}
