package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.server.lobby.LobbyEventListener;
import vermesa.lotr.view.console.Context;

import java.io.Serializable;
import java.rmi.RemoteException;

public class LobbyClientEventListener implements LobbyEventListener, Serializable {

    public LobbyClientEventListener() {

    }

    @Override
    public void onEnemyLeave() throws RemoteException {

    }
}
