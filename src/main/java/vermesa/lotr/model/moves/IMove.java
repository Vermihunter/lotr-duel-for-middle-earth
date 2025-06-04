package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.basic_actions.MultiChoiceAction;
import vermesa.lotr.model.actions.basic_actions.TakeAllActions;
import vermesa.lotr.model.game.GameContext;
import vermesa.lotr.model.game.GameState;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IMove extends IAction, Serializable {
    default int coinsToPlay() {
        return 0;
    }

    static ActionResult performMultiStageMove(GameContext ctx, GameState state, List<? extends IAction> actions) {
        boolean shiftRounds = true;
        ArrayList<List<IMove>> followUpActions = new ArrayList<>();

        // Executing sub-actions
        for (var action : actions) {
            if (action == null) {
                throw new IllegalArgumentException("");
            }

            var subResult = action.action(ctx, state);

            if (subResult == null) {
                throw new IllegalArgumentException("");
            }

            if (!subResult.shiftNextPlayer()) {
                shiftRounds = false;
            }

            if (!subResult.followUpActions().isEmpty()) {
                followUpActions.addAll(subResult.followUpActions());
            }
        }


        if (shiftRounds && !followUpActions.isEmpty()) {
            throw new IllegalArgumentException("");
        }

        return new ActionResult(followUpActions, shiftRounds);
    }

    default void extractCoins(GameContext ctx, GameState state, int coinsToExtract) {
        Player playerOnMove = state.getPlayerOnMove();
        if (playerOnMove.getCoins() < coinsToExtract) {
            throw new IllegalArgumentException("The current player has not enough coins");
        }

        playerOnMove.removeCoins(coinsToExtract);
        state.putBackCoinsToReserve(coinsToExtract);
    }
}
