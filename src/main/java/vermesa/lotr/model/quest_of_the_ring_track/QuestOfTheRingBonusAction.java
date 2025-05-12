package vermesa.lotr.model.quest_of_the_ring_track;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.util.List;

public record QuestOfTheRingBonusAction(int pos, List<IAction> action) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
