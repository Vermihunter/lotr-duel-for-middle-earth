package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;

import java.time.Duration;
import java.util.List;

/**
 * Represents a controller component that is used when a local human player plays the game
 */
public class HumanPlayerController implements LotrController {
    private final IOpponentController opponentController;
    private final Player humanPlayer;
    private final Game game;
    private final IEnemyMoveMadeListener listener;

    /**
     * Sets the minimum time for the human player to wait for the moves so the
     * behavior of the opponent player was more human-like
     */
    private final Duration userExperienceWait; // Duration.ofMillis(2500);

    private final Object lock;

    public HumanPlayerController(IOpponentController opponentController, Player humanPlayerType, Game game, IEnemyMoveMadeListener listener, Duration userExperienceWait, Object lock) {
        this.opponentController = opponentController;
        this.humanPlayer = humanPlayerType;
        this.game = game;
        this.listener = listener;
        this.userExperienceWait = userExperienceWait;
        this.lock = lock;
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Game getGame() {
        return game;
    }

    public void start() {
        playMove();
    }

    public void sendHumanPlayerMoves(List<IAction> moves) {
        game.makeMove(moves);
    }

    public void makeNextAIPlayerMove() {

    }

    private void playMove() {
        //System.out.println("HumanPlayerController::playMove");
        synchronized (lock) {

            while (true) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }

                Player playerOnMove = game.getState().getPlayerOnMove();
                if (playerOnMove == humanPlayer) {
                    continue;
                }

                var start = System.currentTimeMillis();
                var movesMade = opponentController.makeMove(game);
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

                listener.listen(movesMade);
            }

        }
    }
}
