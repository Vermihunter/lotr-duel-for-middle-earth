package vermesa.lotr.ai_players;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.player.Player;

import java.util.List;
import java.util.Random;

public class RandomAIPlayer extends AbstractAIPlayer {
    private final Random random;

    public RandomAIPlayer(Random random, Player me) {
        super(me);
        this.random = random;
    }

    @Override
    protected List<IAction> chooseMoves(List<List<IMove>> possibleMoves) {
        return possibleMoves.stream()
                .map(moveGroup -> (IAction) moveGroup.get(random.nextInt(moveGroup.size())))
                .toList();
    }

}
