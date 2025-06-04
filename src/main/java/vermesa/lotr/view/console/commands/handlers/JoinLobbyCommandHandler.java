package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.CommandResourceBundleKeys;
import vermesa.lotr.controllers.client.ClientLobbyServiceFactory;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.*;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.annotations.CommandInfo;
import vermesa.lotr.view.console.commands.AppState;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;

import java.rmi.RemoteException;
import java.util.List;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;

@CommandInfo(
        nameKey = CommandResourceBundleKeys.JOIN_LOBBY_STATE_NAME,
        descKey = CommandResourceBundleKeys.JOIN_LOBBY_HELP_MESSAGE,
        states = {AppState.MAIN}
)
public class JoinLobbyCommandHandler extends CommandHandler {

    public JoinLobbyCommandHandler(Context context, String name, String description) {
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
            if (ClientLobbyServiceFactory.lobbyService == null) {
                context.out.println(">>> Unable to connect to the server.");
                return CommandResult.OK(console);
            }
        }

        List<LobbyInfo> lobbies;
        try {
            lobbies = lobbyService.listLobbies();
        } catch (RemoteException e) {
            // ignore
            return new CommandResult(CommandResultType.CONTINUE, "Error while retrieving the lobbies", true, console.getCurrentAppState());
        }

        if (lobbies == null || lobbies.isEmpty()) {
            return new CommandResult(CommandResultType.CONTINUE, "No available lobbies", true, console.getCurrentAppState());
        }


        context.out.println("\tAvailable lobbies: " + lobbies.size());
        for (var lobby : lobbies) {
            context.out.println("\t\t- " + lobby.name());
        }

        while (true) {

            context.out.print(">> Lobby to join: ");
            String lobbyName = context.scanner.nextLine();

            try {
                LobbyInfo lobby = lobbyService.joinLobby(lobbyName);
                if (lobby == null) {
                    context.out.println(">>> Invalid lobby name: " + lobbyName);
                    continue;
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            context.out.println(">>> Successfully joined lobby " + lobbyName);
            break;
        }

        return CommandResult.OK(console);
    }

}
