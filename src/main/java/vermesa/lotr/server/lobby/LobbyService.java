package vermesa.lotr.server.lobby;

import vermesa.lotr.controllers.IOpponentController;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface LobbyService extends Remote {

    /**
     * Lists the currently created lobbies on the server
     *
     * @return The list of created lobbies where there is exactly one player waiting for an opponent
     * @throws RemoteException RMI implementation detail
     */
    List<LobbyInfo> listLobbies() throws RemoteException;

    /**
     * Creates a lobby
     *
     * @return The UUID of the newly created lobby
     * @throws RemoteException
     */
    UUID createLobby(String lobbyName) throws RemoteException;

    /**
     * @param lobbyName
     * @return
     * @throws RemoteException
     */
    LobbyInfo joinLobby(String lobbyName) throws RemoteException;

    /**
     * Leaves the lobby that the player is currently in
     * If the player is currently not in a lobby, it is ignored
     *
     * @throws RemoteException
     */
    void leaveCurrentLobby() throws RemoteException;

    /**
     * Starts a game and returns a communication interface {@link IOpponentController} to communicate
     *
     * @return
     * @throws RemoteException
     */
    IOpponentController startGame() throws RemoteException;

}
