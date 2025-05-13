package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

public record DiscardEnemyGreyCardAction(SkillSet skillsetToDiscard) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player otherPlayer = state.getNextPlayerOnMove();
        boolean skillSetWasPresent = otherPlayer.getSkills().discardSkillSet(skillsetToDiscard);
        if (!skillSetWasPresent) {
            throw new IllegalArgumentException("The requested SkillSet was not present among the player's SkillSets");
        }

        return ActionResult.OK;
    }
}
