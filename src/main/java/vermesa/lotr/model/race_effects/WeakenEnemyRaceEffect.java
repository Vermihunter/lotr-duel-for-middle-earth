package vermesa.lotr.model.race_effects;


/**
 * Ents race effect -> Choose, 3 times between:
 * - Remove 1 enemy Unit
 * - Opponent loses 1 Coin
 * - Complemete 1 movement on the central board
 */
public class WeakenEnemyRaceEffect implements IRaceEffect {
    @Override
    public RaceEffectApplyAttributes apply() {
        return null;
    }

    @Override
    public boolean isOneTimeCard() {
        return false;
    }
}
