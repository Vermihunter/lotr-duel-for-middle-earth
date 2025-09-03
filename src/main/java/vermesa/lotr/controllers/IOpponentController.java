package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.player.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Represents a controller type that plays against the local human player
 * It can be an AI type or another human player playing over a network
 */
public interface IOpponentController extends Remote {
    MoveResult makeMove(List<IAction> move) throws RemoteException;

    List<List<IMove>> getPossibleMoves() throws RemoteException;

    Game getGame() throws RemoteException;

    /**
     * Returns the {@link Player} that the opponent has
     *
     * @return
     */
    Player getHumanPlayer() throws RemoteException;

    Player getOpponentPlayer() throws RemoteException;
}
