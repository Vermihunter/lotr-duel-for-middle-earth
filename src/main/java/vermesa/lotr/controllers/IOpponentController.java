package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.MoveResult;

import java.util.List;

/**
 * Represents a controller type that plays against the local human player
 * It can be an AI type or another human player playing over a network
 */
public interface IOpponentController {
    MoveResult makeMove(Game game);
}
