package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;

import java.util.List;

/**
 * Represents a controller type that plays against the local human player
 * It can be an AI type or another human player playing over a network
 */
public interface IOpponentController {
    List<IAction> makeMove(Game game);
}
