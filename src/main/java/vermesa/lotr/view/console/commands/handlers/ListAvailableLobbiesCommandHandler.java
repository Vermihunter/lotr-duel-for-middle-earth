package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.config.ServerServiceKeys;
import vermesa.lotr.server.lobby.*;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.CommandResult;
import vermesa.lotr.view.console.commands.CommandResultType;
import vermesa.lotr.view.console.event_handlers.LobbyClientEventListener;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class ListAvailableLobbiesCommandHandler extends CommandHandler {
    private final static LobbyServiceFactoryRemote factory;
    private final static LobbyService lobbyService;

    static {
        ResourceBundle serverBundle = ResourceBundle.getBundle("server");
        String host = serverBundle.getString("host");
        int port = Integer.parseInt(serverBundle.getString("port"));

        String lobbyServiceFactoryUrl = "rmi://" + host + ":" + port + "/" + ServerServiceKeys.LOBBY_SERVICE_FACTORY_NAME;
        try {
            var remoteObj = Naming.lookup(lobbyServiceFactoryUrl);
            factory = ((LobbyServiceFactoryRemote) remoteObj);
            lobbyService = factory.getLobbyService(new LobbyClientEventListener(null), UUID.randomUUID());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }


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
