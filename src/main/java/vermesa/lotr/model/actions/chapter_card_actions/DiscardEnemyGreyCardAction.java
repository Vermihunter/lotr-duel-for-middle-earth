package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;

/**
 * Discards a grey chapter card from the enemy player
 *
 * @param skillSetToDiscard The skill set to discard
 */
public record DiscardEnemyGreyCardAction(SkillSet skillSetToDiscard) implements IMove, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        Player otherPlayer = state.getNextPlayerOnMove();
        boolean skillSetWasPresent = otherPlayer.getSkills().discardSkillSet(skillSetToDiscard);
        if (!skillSetWasPresent) {
            throw new IllegalArgumentException("The requested SkillSet was not present among the player's SkillSets");
        }

        return ActionResult.OK;
    }
}
