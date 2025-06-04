package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.client.ClientLobbyServiceFactory;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;

import java.rmi.RemoteException;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;


@CommandInfo(
        nameKey = CommandResourceBundleKeys.CREATE_LOBBY_STATE_NAME,
        descKey = CommandResourceBundleKeys.CREATE_LOBBY_HELP_MESSAGE,
        states = {AppState.MAIN}
)
public class CreateLobbyCommandHandler extends CommandHandler {

    public CreateLobbyCommandHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        if (lobbyService == null) {
            try {
                LobbyEventListener lobbyEventListener = new LobbyClientEventListener(console);
                GameEventListener gameEventListener = new NetworkEnemyMoveMadeListener(context.enemyMoveMadeListener, context);
                ClientLobbyServiceFactory.initialize(lobbyEventListener, gameEventListener);
                context.lobbyClientEventListener = lobbyEventListener;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            if (lobbyService == null) {
                context.out.println(">>> Unable to connect to the server.");
                return CommandResult.OK(console);
            }
        }

        while (true) {
            context.out.print(">> Enter the name of the lobby: ");

            String lobbyName = context.scanner.nextLine();
            if (lobbyName.isEmpty()) {
                context.out.println("Invalid lobby name");
                continue;
            }

            try {
                lobbyService.createLobby(lobbyName);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            break;
        }

        context.out.println(">> Successfully created a lobby.");

        return CommandResult.OK(console);
    }
}
