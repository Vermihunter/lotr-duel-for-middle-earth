package vermesa.lotr.model.player;

import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.ExtendedSkillSet;

import java.util.HashSet;

public class Player {
    private final ExtendedSkillSet skillSet;

    private final HashSet<ChainingSymbols> chainingSymbols;

    private final PlayerState playerState;

    // Number of cards per race
    private final int[] supportingRaces;
    private int coins;
    private int units;
    private int towers;

    public Player(int startingCoins, int units, int towers) {
        this.coins = startingCoins;
        this.units = units;
        this.towers = towers;
        this.skillSet = new ExtendedSkillSet();
        this.chainingSymbols = new HashSet<>();
        this.supportingRaces = new int[Race.values().length];
        this.playerState = new PlayerState();
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void addSupportingRace(Race race) {
        supportingRaces[race.ordinal()]++;
    }

    public final int[] getSupportingRaces() {
        return supportingRaces;
    }

    public ExtendedSkillSet getSkills() {
        return skillSet;
    }

    public int getCoins() { return coins; }
    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins) {
        this.coins -= coins;
    }

    public int getUnits() {
        return units;
    }
    public int getTowers() {
        return towers;
    }
}
