package vermesa.lotr.model.player;

import vermesa.lotr.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {
    // The combined value of all non-optional skills
    private final SkillSet skills;

    private final List<SkillSet> allSkills;
    private final List<SkillSet> optionalSkills;

    private final HashSet<ChainingSymbol> chainingSymbols;

    // Number of cards per race
    private final int[] supportingRaces;
    private int coins;
    private int units;
    private int towers;

    public Player(int startingCoins, int units, int towers) {
        this.coins = startingCoins;
        this.units = units;
        this.towers = towers;
        this.skills = new SkillSet();
        this.allSkills = new ArrayList<>();
        this.optionalSkills = new ArrayList<>();
        this.chainingSymbols = new HashSet<>();
        this.supportingRaces = new int[Race.values().length];
    }

    public SkillSet getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.addSkill(skill, 1);
    }

    public int getCoins() { return coins; }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public int getUnits() {
        return units;
    }

    public int getTowers() {
        return towers;
    }
}
