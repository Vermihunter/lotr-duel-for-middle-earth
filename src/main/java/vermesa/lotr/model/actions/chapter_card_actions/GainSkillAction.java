package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

import java.io.Serializable;

/**
 * Gains a {@link vermesa.lotr.model.skills.SkillSet}
 * It is the implementation of Grey chapter cards from the physical copy
 *
 * @param skillsToGain
 */
public record GainSkillAction(SkillSet skillsToGain) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        playerOnMove.getSkills().addSkillSet(skillsToGain);

        return ActionResult.OK;
    }
}
