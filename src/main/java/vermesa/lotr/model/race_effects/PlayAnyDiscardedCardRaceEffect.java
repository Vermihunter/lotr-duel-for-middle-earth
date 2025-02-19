package vermesa.lotr.model.race_effects;

public class PlayAnyDiscardedCardRaceEffect implements IRaceEffect {
    @Override
    public RaceEffectApplyAttributes apply() {
        return null;
    }

    @Override
    public boolean isOneTimeCard() {
        return false;
    }
}
