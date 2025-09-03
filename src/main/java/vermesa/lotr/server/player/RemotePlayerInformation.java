package vermesa.lotr.server.player;

import vermesa.lotr.server.game.GameEventListener;
import vermesa.lotr.server.lobby.LobbyEventListener;

import java.util.UUID;

public record RemotePlayerInformation(UUID id, LobbyEventListener lobbyEventListener,
                                      GameEventListener gameEventListener) {
}
