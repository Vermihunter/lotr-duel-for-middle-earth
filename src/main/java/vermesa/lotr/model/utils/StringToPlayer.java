package vermesa.lotr.model.utils;

import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;

/**
 * Player utils
 */
public class StringToPlayer {
    /**
     * Helper function to get the player from the name
     *
     * @param playerName       Name of the player to get
     * @param fellowshipPlayer "Singleton" fellowship player
     * @param sauronPlayer     "Singleton" sauron player
     * @return The player that the playerName represents or {@code null} if the player name is badly defined
     */
    public static Player fromString(String playerName, FellowshipPlayer fellowshipPlayer, SauronPlayer sauronPlayer) {
        if (playerName.equals(FellowshipPlayer.NAME)) {
            return fellowshipPlayer;
        }

        if (playerName.equals(SauronPlayer.NAME)) {
            return sauronPlayer;
        }

        return null;
    }
}
