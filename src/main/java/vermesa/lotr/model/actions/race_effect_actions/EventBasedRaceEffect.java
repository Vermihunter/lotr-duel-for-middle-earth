package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.PlayerState;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Adds callback to given event for the current player
 */
public record EventBasedRaceEffect(RaceEffectCallbackEventType callbackEventType,
                                   IAction action) implements IAction, Serializable {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        PlayerState playerOnMoveState = state.getPlayerOnMove().getPlayerState();
        var playerRaceEffectHandler = playerOnMoveState.getRaceEffectCallbackEventHandler();
        playerRaceEffectHandler.addEventHandler(callbackEventType, action);

        return ActionResult.OK;
    }
}
