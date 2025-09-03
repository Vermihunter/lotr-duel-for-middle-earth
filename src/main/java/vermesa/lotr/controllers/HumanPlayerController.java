package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.player.Player;

import java.rmi.RemoteException;
import java.time.Duration;
import java.util.List;

/**
 * Represents a controller component that is used when a local human player plays the game
 */
public class HumanPlayerController implements LotrController {
    private final IOpponentController opponentController;
    private final IGameHasEndedListener gameHasEndedListener;
    private final Player humanPlayer;
    private final Game game;
    private final IEnemyMoveMadeListener listener;

    /**
     * Sets the minimum time for the human player to wait for the moves so the
     * behavior of the opponent player was more human-like
     */
    private final Duration userExperienceWait; // Duration.ofMillis(2500);

    private final Object lock;

    public HumanPlayerController(IOpponentController opponentController, IGameHasEndedListener gameHasEndedListener, Player humanPlayerType, Game game, IEnemyMoveMadeListener listener, Duration userExperienceWait, Object lock) {
        this.opponentController = opponentController;
        this.gameHasEndedListener = gameHasEndedListener;
        this.humanPlayer = humanPlayerType;
        this.game = game;
        this.listener = listener;
        this.userExperienceWait = userExperienceWait;
        this.lock = lock;
    }

    public List<List<IMove>> getPossibleMoves() throws RemoteException {
        return opponentController.getPossibleMoves();
    }

    public MoveResult sendMove(List<IAction> move) throws RemoteException {
        return opponentController.makeMove(move);
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Game getGame() {
        return game;
    }

    /*
    public void start() {
        playMove();
    }


    private void playMove() {
        if (game.state().getCurrentGameState() != CurrentGameState.HAS_NOT_ENDED) {
            return;
        }

        synchronized (lock) {

            while (true) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }

                Player playerOnMove = game.state().getPlayerOnMove();
                if (playerOnMove == humanPlayer) {
                    continue;
                }

                if (game.state().getCurrentGameState() != CurrentGameState.HAS_NOT_ENDED) {
                    return;
                }

                var start = System.currentTimeMillis();
                MoveResult moveResult = null;
                try {
                    moveResult = opponentController.makeMove();
                } catch (java.rmi.RemoteException e) {
                    throw new RuntimeException(e);
                }

                if(moveResult == null) {
                    continue;
                }

                var movesMade = moveResult.movesTaken();
                var end = System.currentTimeMillis();

                var moveMadeDuration = end - start;
                var millisToWait = userExperienceWait.toMillis();

                if (millisToWait > moveMadeDuration) {
                    try {
                        Thread.sleep(millisToWait - moveMadeDuration);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }

                listener.listen(movesMade, game.state().getPlayerOnMove() == humanPlayer);

                if (game.state().getCurrentGameState() != CurrentGameState.HAS_NOT_ENDED) {
                    gameHasEndedListener.listen(moveResult.currentGameState());
                    return;
                }
            }

        }
    }

     */
}
