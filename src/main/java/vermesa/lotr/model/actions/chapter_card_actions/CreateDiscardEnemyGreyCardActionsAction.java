package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

import java.util.List;

public class CreateDiscardEnemyGreyCardActionsAction implements IAction {
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player otherPlayer = state.getNextPlayerOnMove();

        List<IAction> followUpActions = otherPlayer.getSkills().getAllSkillSets().stream()
                .map(greyCardSkillSet -> (IAction) new DiscardEnemyGreyCardAction(greyCardSkillSet))
                .toList();

        return new ActionResult(followUpActions, false);
    }
}
