package vermesa.lotr.controllers;

import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract class OpponentController implements IOpponentController, Serializable, Remote {
    protected final OpponentControllerContext context;

    protected OpponentController(OpponentControllerContext context) {
        this.context = context;
    }

    @Override
    public Game getGame() throws RemoteException {
        return context.game();
    }

    @Override
    public Player getHumanPlayer() {
        return context.humanPlayer();
    }

    @Override
    public Player getOpponentPlayer() throws RemoteException {
        return context.opponentPlayer();
    }
}
