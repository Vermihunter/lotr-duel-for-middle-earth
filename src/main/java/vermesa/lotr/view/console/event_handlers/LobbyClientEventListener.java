package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.view.console.ConsoleView;
import vermesa.lotr.view.console.commands.AppState;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LobbyClientEventListener extends UnicastRemoteObject implements LobbyEventListener, Serializable {
    private final ConsoleView consoleView;


    public LobbyClientEventListener(ConsoleView consoleView) throws RemoteException {
        this.consoleView = consoleView;
    }

    @Override
    public void onEnemyLeave() throws RemoteException {

    }

    @Override
    public void onGameStarted(IOpponentController controller) throws RemoteException {
        System.out.println("The opponent has started the game");
        consoleView.switchToView(AppState.GAME_PLAY);
        consoleView.getContext().controller = controller;
        consoleView.printHelp();
    }
}
