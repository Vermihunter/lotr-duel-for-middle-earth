package vermesa.lotr.model.quest_of_the_ring_track;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.util.List;

/**
 * Represents a bonus on the {@link QuestOfTheRingTrack}
 * Note that both players have the same bonuses and the {@link #pos} parameter is relative to the
 * beginning of the track i.e. the number of moves made by the players should be compared to {@link #pos}
 *
 * @param pos    Position where the bonus is at
 * @param action Action bonuses that are invoked when the bonus is reached
 */
public record QuestOfTheRingBonusAction(int pos, List<IAction> action) implements IAction {

    /**
     * TODO: implement
     * @param ctx Context of the game
     * @param state State of the game
     * @return
     */
    @Override
    public ActionResult action(GameContext ctx, GameState state) {

        return null;
    }
}
