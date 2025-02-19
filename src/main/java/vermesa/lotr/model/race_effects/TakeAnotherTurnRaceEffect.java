package vermesa.lotr.model.race_effects;

public class TakeAnotherTurnRaceEffect implements IRaceEffect {
    @Override
    public RaceEffectApplyAttributes apply() {
        return null;
    }

    @Override
    public boolean isOneTimeCard() {
        return true;
    }
}
