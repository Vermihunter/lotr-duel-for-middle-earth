package vermesa.lotr.model.utils;

import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;

public class StringToPlayer {
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
