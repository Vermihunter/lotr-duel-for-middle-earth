package vermesa.lotr.server.lobby;

import vermesa.lotr.server.player.RemotePlayerInformation;

import java.rmi.RemoteException;
import java.util.UUID;

public class Lobby {
    private final String name;
    private RemotePlayerInformation owner;
    private RemotePlayerInformation enemy;

    //private UUID ownerId;
    //private UUID enemyId;

    protected Lobby(String lobbyName, RemotePlayerInformation owner) throws RemoteException {
        this.name = lobbyName;
        this.owner = owner;
        this.enemy = null;
    }

    public LobbyInfo extractInfo() {
        return new LobbyInfo(name);
    }


    public String getName() {
        return name;
    }


    public RemotePlayerInformation getOwner() {
        return owner;
    }

    public void setOwner(RemotePlayerInformation owner) {
        this.owner = owner;
    }


    public RemotePlayerInformation getEnemy() {
        return enemy;
    }

    public void setEnemy(RemotePlayerInformation enemy) {
        this.enemy = enemy;
    }

    public LobbyClientState getState(UUID clientId) {
        if (clientId.equals(owner.id())) {
            return LobbyClientState.OWNER;
        }

        if (enemy == null) {
            return LobbyClientState.NONE;
        }

        if (clientId.equals(enemy.id())) {
            return LobbyClientState.OPPONENT;
        }

        return LobbyClientState.NONE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lobby lobby = (Lobby) o;
        return name.equals(lobby.name) &&
                owner.id().equals(lobby.owner.id()) &&
                enemy.id().equals(lobby.enemy.id());
    }
}
