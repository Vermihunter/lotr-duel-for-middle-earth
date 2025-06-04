package vermesa.lotr.server.lobby;

import vermesa.lotr.server.game.GameEventListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface LobbyServiceFactoryRemote extends Remote {
    LobbyService getLobbyService(LobbyEventListener clientListener, GameEventListener gameEventListener, UUID clientID) throws RemoteException;
}
