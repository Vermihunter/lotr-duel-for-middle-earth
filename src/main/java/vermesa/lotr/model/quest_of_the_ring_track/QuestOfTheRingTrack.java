package vermesa.lotr.model.quest_of_the_ring_track;

import jdk.jshell.spi.ExecutionControl;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.model.quest_of_the_ring_track.*;

import java.util.List;

public class QuestOfTheRingTrack {
    private final int width;
    private final List<QuestOfTheRingBonusAction> bonusActions;
    private int fellowshipPlayerIndex;
    private int sauronPlayerIndex;

    public QuestOfTheRingTrack(int width, int startingFellowshipPlayerIndex, int startingSauronPlayerIndex, List<QuestOfTheRingBonusAction> bonusActions) {
        this.width = width;
        this.fellowshipPlayerIndex = startingFellowshipPlayerIndex;
        this.sauronPlayerIndex = startingSauronPlayerIndex;
        this.bonusActions = bonusActions;
    }

    public int getFellowshipPlayerIndex() {
        return fellowshipPlayerIndex;
    }

    public int getSauronPlayerIndex() {
        return sauronPlayerIndex;
    }

    public int getWidth() {
        return width;
    }

    public void MovePlayer(Player player, int move) {
        switch (player) {
            case SauronPlayer ignored -> MovePlayer(player, sauronPlayerIndex, move);
            case FellowshipPlayer ignored -> MovePlayer(player, fellowshipPlayerIndex, move);
            default -> throw new IllegalStateException("Unexpected value: " + player);
        };
    }

    private void MovePlayer(Player player, int ind, int move) {

    }

}
