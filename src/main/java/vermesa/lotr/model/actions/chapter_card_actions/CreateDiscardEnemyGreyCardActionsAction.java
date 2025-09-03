package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An action that creates follow-up moves to discard concrete enemy grey-cards
 * Note that the grey chapter cards in the physical copy are simply represented
 * with a {@link vermesa.lotr.model.skills.SkillSet} object.
 */
public class CreateDiscardEnemyGreyCardActionsAction implements IAction, Serializable {
    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player otherPlayer = state.getNextPlayerOnMove();

        // Collect the SkillSets to discard
        List<IMove> followUpActions = otherPlayer.getSkills().getAllSkillSets().stream()
                .map(greyCardSkillSet -> (IMove) new DiscardEnemyGreyCardAction(greyCardSkillSet))
                .toList();


        ArrayList<List<IMove>> followUpActionGroup = new ArrayList<>();
        if (!followUpActions.isEmpty()) {
            followUpActionGroup.add(followUpActions);
        }

        // Note that if the enemy has no Grey cards, there is nothing to discard and player shift is wanted
        return new ActionResult(followUpActionGroup, followUpActions.isEmpty());
    }
}
