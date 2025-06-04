package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.PlayerState;

import java.io.Serializable;

public record GainAttributeRaceEffect(RaceEffectAttributes raceEffectAttribute) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        PlayerState playerOnMoveState = state.getPlayerOnMove().getPlayerState();
        playerOnMoveState.addRaceEffectAttribute(raceEffectAttribute);

        return ActionResult.OK;
    }
}
