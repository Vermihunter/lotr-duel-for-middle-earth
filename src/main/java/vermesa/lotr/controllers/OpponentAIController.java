package vermesa.lotr.controllers;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.MoveResult;

import java.util.List;

public class OpponentAIController implements IOpponentController {
    private final IAIPlayer aiPlayer;

    public OpponentAIController(IAIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    @Override
    public MoveResult makeMove(Game game) {
        return aiPlayer.play(game);
    }
}
