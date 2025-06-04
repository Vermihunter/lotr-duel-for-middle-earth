package vermesa.lotr.view.console.commands.handlers;

import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.event_handlers.NetworkEnemyMoveMadeListener;

import java.rmi.RemoteException;

import static vermesa.lotr.controllers.client.ClientLobbyServiceFactory.lobbyService;

public class StartNetworkGameConfigurationHandler extends StartGameConfigurationHandler {
    public StartNetworkGameConfigurationHandler(Context context, String name, String description) {
        super(context, name, description);
    }

    @Override
    protected IOpponentController getOpponentController() {
        try {
            context.enemyNetworkMoveMadeListener = new NetworkEnemyMoveMadeListener(context.enemyMoveMadeListener);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        try {
            return lobbyService.startGame();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
