package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;

public record GainSkillAction(SkillSet skillsToGain) implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player playerOnMove = state.getPlayerOnMove();
        playerOnMove.getSkills().addSkillSet(skillsToGain);

        return ActionResult.OK;
    }
}
