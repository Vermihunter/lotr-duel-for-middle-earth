package vermesa.lotr.model.actions.chapter_card_actions;

import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.ChapterCardPlayMove;
import vermesa.lotr.model.moves.IMove;

import java.util.List;

/**
 * It is the common implementation of the following actions:
 * <ul>
 *     <li>Part of Mordor landmark tile</li>
 *     <li>One of the wizards' race effects</li>
 * </ul>
 * Creates {@link ChapterCardPlayMove} for every chapter card that has been discarded so far
 */
public record PlayDiscardedChapterCardAction() implements IAction {

    @Override
    public ActionResult action(GameContext ctx, GameState state) {
        // Go through all discarded cards and create a ChapterCardPlayMove for each of them
        List<IMove> followUpActions = state.getDiscardedChapterCards().stream()
                .map(discardedChapterCard -> (IMove) ChapterCardPlayMove.fromDiscard(discardedChapterCard))
                .toList();

        // No chapter card
        if (followUpActions.isEmpty()) {
            return new ActionResult(List.of(), true);
        }

        return new ActionResult(List.of(followUpActions), false);
    }
}
