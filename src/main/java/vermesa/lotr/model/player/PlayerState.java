package vermesa.lotr.model.player;

import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventHandler;

import java.util.HashSet;

public class PlayerState {
    private final HashSet<RaceEffectAttributes> raceEffectAttributes;
    private final RaceEffectCallbackEventHandler raceEffectCallbackEventHandler;

    public PlayerState() {
        this.raceEffectAttributes = new HashSet<>();
        this.raceEffectCallbackEventHandler = new RaceEffectCallbackEventHandler();
    }

    public void addRaceEffectAttribute(RaceEffectAttributes raceEffectAttribute) {
        this.raceEffectAttributes.add(raceEffectAttribute);
    }

    public RaceEffectCallbackEventHandler getRaceEffectCallbackEventHandler() {
        return raceEffectCallbackEventHandler;
    }

}
