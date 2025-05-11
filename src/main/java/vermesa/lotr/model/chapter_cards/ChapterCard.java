package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.*;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ChapterCard implements IMove {
    /**
     * A list of cards that are dependent on this card i.e. the list of cards that will be
     * available only after playing this card.
     */

    private final List<ChapterCard> dependentOnThis;
    private final SkillSet requiredSkillSet;
    private final ChainingSymbol chainingSymbol;
    private boolean isFaceUp;
    private int remainingDependencies;
    private final ArrayList<IAction> actions;
    private final ChapterCardContext context;

    public ChapterCard(SkillSet requiredSkillSet, ChainingSymbol chainingSymbol, ArrayList<IAction> actions, ChapterCardContext context) {
        this.dependentOnThis = new ArrayList<>();
        this.requiredSkillSet = requiredSkillSet;
        this.chainingSymbol = chainingSymbol;
        this.isFaceUp = false;
        this.remainingDependencies = 0;
        this.actions = actions;
        this.context = context;
    }

    public ChainingSymbol getChainingSymbol() {
        return chainingSymbol;
    }

    public void addDependency(ChapterCard card) {
        card.dependentOnThis.add(this);
        remainingDependencies++;
    }

    public boolean isPlayableByPlayer(Player player) {
        return false;
    }

    @Override
    public MoveResult playMove(GameContext ctx, GameState state) {
        return null;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void reveal() { isFaceUp = true;}
}
