package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.skills.SkillSet;

import java.util.List;

public record LandmarkTile(Region region, SkillSet requiredSkillset, List<IAction> actions) implements IMove {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
