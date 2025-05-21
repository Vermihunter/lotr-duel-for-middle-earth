package vermesa.lotr.model.quest_of_the_ring_track;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;

import java.util.List;

public class QuestOfTheRingTrack {
    /**
     * Width of the track - note that this width does not map to the original width of the Quest of the ring track
     * in the physical copy of the game. To make computation easy, the fellowship player starts at the "middle" of
     * the track and the sauron player starts at 0. If the width of the quest of the ring track in the physical copy
     * is physicalWidth then the width variable is computed by 2 * physicalWidth - 1
     */
    private final int width;

    /**
     * Bonus actions with positions specifying when the bonuses are reached
     */
    private final List<QuestOfTheRingBonusAction> bonusActions;


    /**
     * Index of the fellowship player on the track
     */
    private int fellowshipPlayerIndex;

    /**
     * Number of moves that the fellowship player has made
     */
    private int fellowshipPlayerMovesMade;

    /**
     * Index of the sauron player of the track
     */
    private int sauronPlayerIndex;

    /**
     * Number of moves that the sauron player has made
     */
    private int sauronPlayerMovesMade;

    public QuestOfTheRingTrack(int width, int startingFellowshipPlayerIndex, int startingSauronPlayerIndex, List<QuestOfTheRingBonusAction> bonusActions) {
        this.width = width;
        this.fellowshipPlayerIndex = startingFellowshipPlayerIndex;
        this.fellowshipPlayerMovesMade = 0;
        this.sauronPlayerIndex = startingSauronPlayerIndex;
        this.sauronPlayerMovesMade = 0;
        this.bonusActions = bonusActions;
    }

    public List<QuestOfTheRingBonusAction> getBonusActions() {
        return bonusActions;
    }

    public int getFellowshipPlayerIndex() {
        return fellowshipPlayerIndex;
    }

    public int getFellowshipPlayerMovesMade() {
        return fellowshipPlayerMovesMade;
    }

    public int getSauronPlayerIndex() {
        return sauronPlayerIndex;
    }

    public int getSauronPlayerMovesMade() {
        return sauronPlayerMovesMade;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Moves a concrete player on the quest of the ring track
     * - Moves the player forward
     * - Applies earned bonuses
     * <p>
     * Note that to the applyBonuses function we pass the number of moves made by the player
     * and not their index since there are the same bonuses available for both players that are
     * indexed relatively to the left end to the board and that way we have to normalize the
     * moves made by the fellowship player since that player does not start at the beginning of
     * the track.
     *</p>
     *
     * @param player      The player that moves on the board
     * @param moveCount   The number of steps made on the board
     * @param gameContext Context of the game (used for the bonus actions)
     * @param gameState   State of the game (used for the bonus actions)
     */
    public void movePlayer(Player player, int moveCount, GameContext gameContext, GameState gameState) {

        switch (player) {
            case SauronPlayer ignored -> {
                applyBonuses(fellowshipPlayerMovesMade, moveCount, gameContext, gameState);

                sauronPlayerIndex += moveCount;
                sauronPlayerMovesMade += moveCount;
            }
            case FellowshipPlayer ignored -> {
                applyBonuses(fellowshipPlayerMovesMade, moveCount, gameContext, gameState);

                sauronPlayerIndex += moveCount;
                fellowshipPlayerIndex += moveCount;
                fellowshipPlayerMovesMade += moveCount;
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + player);
            }
        }
    }

    /**
     * Checks if some bonuses have been earned and if so, applies them
     *
     * @param ind         Index of the player before the move
     * @param moveCount   Number of moves to make on the track
     * @param gameContext Context of the game (used for the bonus actions)
     * @param gameState   State of the game (used for the bonus actions)
     * @return The new position of the player
     */
    private int applyBonuses(int ind, int moveCount, GameContext gameContext, GameState gameState) {
        int newPlayerPosition = ind + moveCount;

        bonusActions.stream()
                .filter(bonus -> bonus.pos() > ind && bonus.pos() <= newPlayerPosition)
                .forEach(bonus -> {
                    applyBonus(bonus.action(), gameContext, gameState);
                });

        return newPlayerPosition;
    }

    /**
     * Applies given set of bonuses
     * Note that the bonus actions do not accept a player to apply the bonuses on because
     * the actions retrieve the player who is on move and the bonuses are applied to that player.
     * Since the move has not finished yet, the player on move has not shifted yet and this is
     * the wanted behavior.
     *
     * @param bonusActions Bonus actions
     * @param gameContext  Context of the game (used for the bonus actions)
     * @param gameState    State of the game (used for the bonus actions)
     */
    private void applyBonus(List<IAction> bonusActions, GameContext gameContext, GameState gameState) {
        for (var bonusAction : bonusActions) {
            bonusAction.action(gameContext, gameState);
        }
    }

}
