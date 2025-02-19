package vermesa.lotr.model.player;

import vermesa.lotr.model.ChainingSymbol;
import vermesa.lotr.model.Skill;
import vermesa.lotr.model.SkillSet;

import java.util.ArrayList;
import java.util.HashSet;

public class Player {
    private final SkillSet skills;
    private final HashSet<ChainingSymbol> chainingSymbols;

    public Player() {
        this.skills = new SkillSet();
        this.chainingSymbols = new HashSet<>();
    }

    public SkillSet getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.addSkill(skill, 1);
    }
}
