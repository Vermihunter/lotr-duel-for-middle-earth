package vermesa.lotr.controllers;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.player.Player;

import java.time.Duration;

public class AIPlayerController {
    private final IAIPlayer aiPlayer;
    private final OpponentControllerContext context;
    private final Object lock;
    private final IEnemyMoveMadeListener listener;
    private final IGameHasEndedListener gameHasEndedListener;
    private final Player humanPlayer;
    private final Duration userExperienceWait; // Duration.ofMillis(2500);

    public AIPlayerController(IAIPlayer aiPlayer, OpponentControllerContext context, Object lock, IEnemyMoveMadeListener listener, IGameHasEndedListener gameHasEndedListener, Player humanPlayer, Duration userExperienceWait) {
        this.aiPlayer = aiPlayer;
        this.context = context;
        this.lock = lock;
        this.listener = listener;
        this.gameHasEndedListener = gameHasEndedListener;
        this.humanPlayer = humanPlayer;
        this.userExperienceWait = userExperienceWait;
    }

    public void run() {
        synchronized (lock) {
            runImpl();
        }
    }

    private void runImpl() {
        while (true) {

            if (context.game().state().getPlayerOnMove().equals(humanPlayer)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }

            makeMoves();
        }
    }

    private void makeMoves() {
        while (true) {
            if (context.game().state().getCurrentGameState() != CurrentGameState.HAS_NOT_ENDED) {
                return;
            }

            if (context.game().state().getPlayerOnMove().equals(humanPlayer)) {
                return;
            }

            var start = System.currentTimeMillis();
            var movesMade = aiPlayer.chooseMoves(context.game());
            var moveResult = context.game().makeMove(movesMade);

            //var movesMade = moveResult.movesTaken();
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

            listener.listen(movesMade, context.game().state().getPlayerOnMove().equals(humanPlayer));

            if (context.game().state().getCurrentGameState() != CurrentGameState.HAS_NOT_ENDED) {
                gameHasEndedListener.listen(moveResult.currentGameState());
                return;
            }
        }
    }

}
