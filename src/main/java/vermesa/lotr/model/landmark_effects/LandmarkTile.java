package vermesa.lotr.model.landmark_effects;

import vermesa.lotr.model.*;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.player.Player;

import java.util.List;

public class LandmarkTile implements IMove {
    private final List<IAction> actions;
    private final SkillSet requiredSkillset;
    private final Region region;

    public LandmarkTile(Region region, SkillSet requiredSkillset, List<IAction> actions) {
        this.actions = actions;
        this.requiredSkillset = requiredSkillset;
        this.region = region;
    }


    @Override
    public boolean isPlayableByPlayer(Player player) {
        return false;
    }

    @Override
    public MoveResult playMove(GameContext ctx, GameState state) {
        return null;
    }
}
