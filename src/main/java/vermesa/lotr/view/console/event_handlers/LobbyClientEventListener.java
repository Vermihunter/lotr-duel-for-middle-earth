package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IOpponentController;
import vermesa.lotr.server.lobby.LobbyEventListener;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LobbyClientEventListener extends UnicastRemoteObject implements LobbyEventListener, Serializable {

    public LobbyClientEventListener() throws RemoteException {
    }

    @Override
    public void onEnemyLeave() throws RemoteException {

    }

    @Override
    public void onGameStarted(IOpponentController controller) throws RemoteException {
        System.out.println("LobbyClientEventListener: onGameStarted");
    }
}
