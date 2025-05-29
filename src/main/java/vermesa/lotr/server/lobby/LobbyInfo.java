package vermesa.lotr.server.lobby;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.UUID;

public record LobbyInfo(UUID id, String name) implements Serializable {

}
