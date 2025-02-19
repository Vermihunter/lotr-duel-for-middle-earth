package vermesa.lotr.model.quest_of_the_ring_track;

import jdk.jshell.spi.ExecutionControl;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.model.quest_of_the_ring_track.*;

public class QuestOfTheRingTrack {
    private PlayerIndex fellowshipPlayerIndex;
    private PlayerIndex sauronPlayerIndex;
    private final int width;

    public QuestOfTheRingTrack(int width) {
        this.width = width;
    }

    public IQuestOfTheRingTrackAction MovePlayer(Player player, int move) {

        return switch (player) {
            case SauronPlayer ignored -> MovePlayer(player, sauronPlayerIndex, move);
            case FellowshipPlayer ignored -> MovePlayer(player, fellowshipPlayerIndex, move);
            default -> throw new IllegalStateException("Unexpected value: " + player);
        };
    }

    private IQuestOfTheRingTrackAction MovePlayer(Player player, PlayerIndex ind, int move) {
        return new EmptyAction();
    }

    private static class PlayerIndex {
        private int index;

        public PlayerIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void addMoves(int move) {
            index += move;
        }
    }
}
