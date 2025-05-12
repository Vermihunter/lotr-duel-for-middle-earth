package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.PlayerState;

/**
 * Adds callback to given event for the current player
 */
public class EventBasedRaceEffect implements IAction {
    private final RaceEffectCallbackEventType callbackEventType;
    private final IAction action;

    public EventBasedRaceEffect(RaceEffectCallbackEventType callbackEventType, IAction action) {
        this.callbackEventType = callbackEventType;
        this.action = action;
    }


    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        PlayerState playerOnMoveState = state.getPlayerOnMove().getPlayerState();
        var playerRaceEffectHandler = playerOnMoveState.getRaceEffectCallbackEventHandler();
        playerRaceEffectHandler.addEventHandler(callbackEventType, action);

        return ActionResult.OK;
    }
}
