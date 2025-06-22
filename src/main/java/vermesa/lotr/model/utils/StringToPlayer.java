package vermesa.lotr.model.utils;

import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;

/**
 * Player utils
 */
public class StringToPlayer {
    /**
     * Helper function to get the player from the name
     *
     * @param playerName       Name of the player to get
     * @return The player that the playerName represents or {@code null} if the player name is badly defined
     */
    public static PlayerType fromString(String playerName) {
        if (playerName.equals(PlayerType.Fellowship.name())) {
            return PlayerType.Fellowship;
        }

        if (playerName.equals(PlayerType.Sauron.name())) {
            return PlayerType.Sauron;
        }

        return null;
    }
}
