package vermesa.lotr.server.lobby;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Lobby extends UnicastRemoteObject {
    private final UUID id;
    private final String name;
    private UUID ownerId;
    private UUID enemyId;

    protected Lobby(UUID lobbyId, String lobbyName, UUID ownerId) throws RemoteException {
        this.id = lobbyId;
        this.name = lobbyName;
        this.ownerId = ownerId;
        this.enemyId = null;
    }

    public LobbyInfo extractInfo() {
        return new LobbyInfo(id, name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }


    public UUID getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(UUID enemyId) {
        this.enemyId = enemyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lobby lobby = (Lobby) o;
        return id.equals(lobby.id) &&
                name.equals(lobby.name) &&
                ownerId.equals(lobby.ownerId) &&
                enemyId.equals(lobby.enemyId);
    }
}
