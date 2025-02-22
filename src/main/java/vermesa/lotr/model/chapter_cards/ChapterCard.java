package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.*;

import java.util.ArrayList;

public class ChapterCard implements IAction {
    /**
     * A list of cards that are dependent on this card i.e. the list of cards that will be
     * available only after playing this card.
     */

    private final ArrayList<ChapterCard> dependentOnThis = new ArrayList<ChapterCard>();
    private final SkillSet requiredSkillSet;
    private final ChainingSymbol chainingSymbol;
    private int remainingDependencies;
    private boolean isFaceUp;

    public ChapterCard(SkillSet requiredSkillSet, ChainingSymbol chainingSymbol, boolean isFaceUp) {
        this.requiredSkillSet = requiredSkillSet;
        this.chainingSymbol = chainingSymbol;
        this.isFaceUp = isFaceUp;
        this.remainingDependencies = 0;
    }

    public void addDependency(ChapterCard card) {
        dependentOnThis.add(card);
        card.remainingDependencies++;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public ChainingSymbol getChainingSymbol() {
        return chainingSymbol;
    }

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        return null;
    }
}
