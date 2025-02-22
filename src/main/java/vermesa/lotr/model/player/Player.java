package vermesa.lotr.model.player;

import vermesa.lotr.model.*;

import java.util.HashSet;

public class Player {
    private final SkillSet skills;
    private final HashSet<ChainingSymbol> chainingSymbols;
    private final int[] supportingRaces;
    private int coins;

    public Player(int startingCoins) {
        this.skills = new SkillSet();
        this.chainingSymbols = new HashSet<>();
        this.supportingRaces = new int[Race.values().length];
        this.coins = startingCoins;
    }

    public SkillSet getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.addSkill(skill, 1);
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public IAction addSupportingRace(Race race) {
        return null;
    }
}
