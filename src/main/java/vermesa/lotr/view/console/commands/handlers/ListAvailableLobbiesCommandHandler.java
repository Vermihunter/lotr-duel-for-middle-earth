package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.server.lobby.*;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;

import java.rmi.RemoteException;
import java.util.List;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;

public class ListAvailableLobbiesCommandHandler extends CommandHandler {

    public ListAvailableLobbiesCommandHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
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

        return CommandResult.OK;
    }

}
