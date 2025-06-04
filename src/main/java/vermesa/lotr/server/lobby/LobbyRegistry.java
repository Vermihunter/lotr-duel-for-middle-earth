package vermesa.lotr.server.lobby;

import java.util.ArrayList;
import java.util.List;

public class LobbyRegistry {
    private static final LobbyRegistry INSTANCE = new LobbyRegistry();

    private final List<Lobby> lobbies;

    public LobbyRegistry() {
        lobbies = new ArrayList<>();
    }

    public static LobbyRegistry getInstance() {
        return INSTANCE;
    }

    public void registerLobby(Lobby lobby) {
        lobbies.add(lobby);
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }

    public Lobby getLobbyByName(String name) {
        return lobbies.stream()
                .filter(lobby -> ((Lobby) (lobby)).getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deleteLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }


}
