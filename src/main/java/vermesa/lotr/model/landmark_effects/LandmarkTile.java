package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.skills.SkillSet;

import java.util.List;

/**
 * Represents a Landmark tile in the game
 *
 * @param region           The region that the Landmark tile places the fortress to
 * @param requiredSkillset The skill set that is required to play this Landmark tile
 * @param actions          The actions that will be executed when playing this card
 */
public record LandmarkTile(Region region, SkillSet requiredSkillset, List<IAction> actions) {
}
