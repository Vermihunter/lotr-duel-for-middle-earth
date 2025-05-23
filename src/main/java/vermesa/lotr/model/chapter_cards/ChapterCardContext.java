package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;

/**
 * Context of a chapter card holding all necessary information about it
 *
 * @param requiredSkillSet          The required skill set to play this card
 * @param gainedChainingSymbol      The chaining symbol that is gained by playing this card
 * @param playForFreeChainingSymbol The chaining symbol that if owned, allows to play this card for free
 * @param actions                   The actions that are executed when the chapter card is played
 * @param coinsToPlay               The number of coins that has to be paid to play this card
 * @param color                     The color type of the card
 */
public record ChapterCardContext(
        SkillSet requiredSkillSet,
        ChainingSymbols gainedChainingSymbol,
        ChainingSymbols playForFreeChainingSymbol,
        ArrayList<IAction> actions,
        int coinsToPlay,
        ChapterCardColors color) {

}