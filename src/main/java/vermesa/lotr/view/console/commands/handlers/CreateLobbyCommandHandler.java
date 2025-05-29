package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.controllers.client.ClientLobbyServiceFactory;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;

import java.rmi.RemoteException;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;

public class CreateLobbyCommandHandler extends CommandHandler {

    public CreateLobbyCommandHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    public CommandResult handleCommand(String[] commandParts, ConsoleView console) {
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

        return CommandResult.OK;
    }
}
