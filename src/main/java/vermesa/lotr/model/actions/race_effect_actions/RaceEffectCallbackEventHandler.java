package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;

/**
 * Every player has one instance → if collects enough race effect cards, adds the given handler
 */
public class RaceEffectCallbackEventHandler {
    private final ArrayList<ArrayList<IAction>> eventHandlers;

    public RaceEffectCallbackEventHandler() {
        this.eventHandlers = new ArrayList<>(RaceEffectCallbackEventType.values().length);
        for (RaceEffectCallbackEventType _ : RaceEffectCallbackEventType.values()) {
            this.eventHandlers.add(new ArrayList<>());
        }
    }

    public void addEventHandler(RaceEffectCallbackEventType eventType, IAction eventHandler) {
        this.eventHandlers.get(eventType.ordinal()).add(eventHandler);
    }

    public void signalEvent(RaceEffectCallbackEventType eventType, GameContext gameContext, GameState gameState) {

        // TODO: use the function defined for running multiple IActions and aggregating result defined in IMove
        // used for example in the 2nd Hobbits Race Effect -> we must return follow ups to choose which region to place the unit to
        for (IAction eventHandler : this.eventHandlers.get(eventType.ordinal())) {
            eventHandler.action(gameContext, gameState);
        }
    }

}
