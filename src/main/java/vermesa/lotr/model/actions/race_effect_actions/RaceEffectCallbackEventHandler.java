package vermesa.lotr.model.actions.race_effect_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;

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
        IMove.performMultiStageMove(gameContext, gameState, this.eventHandlers.get(eventType.ordinal()));
    }

}
