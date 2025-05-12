package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;

public record ChapterCardContext(
        SkillSet requiredSkillSet,
        ChainingSymbols gainedChainingSymbol,
        ChainingSymbols playForFreeChainingSymbol,
        ArrayList<IAction> actions,
        int coinsToPlay,
        ChapterCardColors color) {

}