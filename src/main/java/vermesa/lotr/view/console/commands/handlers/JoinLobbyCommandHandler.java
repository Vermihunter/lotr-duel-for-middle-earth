package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.controllers.client.ClientLobbyServiceFactory;
import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.*;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;

import java.rmi.RemoteException;
import java.util.List;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;

public class JoinLobbyCommandHandler extends CommandHandler {

    public JoinLobbyCommandHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
        if (lobbyService == null) {
            try {
                LobbyEventListener lobbyEventListener = new LobbyClientEventListener();
                GameEventListener gameEventListener = new NetworkEnemyMoveMadeListener(context.enemyMoveMadeListener);
                ClientLobbyServiceFactory.initialize(lobbyEventListener, gameEventListener);
                context.lobbyClientEventListener = lobbyEventListener;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            if (ClientLobbyServiceFactory.lobbyService == null) {
                context.out.println(">>> Unable to connect to the server.");
                return CommandResult.OK;
            }
        }

        List<LobbyInfo> lobbies;
        try {
            lobbies = lobbyService.listLobbies();
        } catch (RemoteException e) {
            // ignore
            return new CommandResult(CommandResultType.CONTINUE, "Error while retrieving the lobbies", true);
        }

        if (lobbies == null || lobbies.isEmpty()) {
            return new CommandResult(CommandResultType.CONTINUE, "No available lobbies", true);
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

        return CommandResult.OK;
    }

}
