package vermesa.lotr.model.player;

import vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes;
import vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventHandler;
import vermesa.lotr.model.chapter_cards.ChainingSymbols;

import java.util.HashSet;

public class PlayerState {
    private final HashSet<RaceEffectAttributes> raceEffectAttributes;
    private final RaceEffectCallbackEventHandler raceEffectCallbackEventHandler;
    private final HashSet<ChainingSymbols> obtainedChainingSymbols;

    public PlayerState() {
        this.raceEffectAttributes = new HashSet<>();
        this.raceEffectCallbackEventHandler = new RaceEffectCallbackEventHandler();
        this.obtainedChainingSymbols = new HashSet<>();
    }

    public void addChainingSymbol(ChainingSymbols symbol) {
        obtainedChainingSymbols.add(symbol);
    }

    public HashSet<ChainingSymbols> getChainingSymbols() {
        return obtainedChainingSymbols;
    }

    public void addRaceEffectAttribute(RaceEffectAttributes raceEffectAttribute) {
        this.raceEffectAttributes.add(raceEffectAttribute);
    }

    public RaceEffectCallbackEventHandler getRaceEffectCallbackEventHandler() {
        return raceEffectCallbackEventHandler;
    }

}
