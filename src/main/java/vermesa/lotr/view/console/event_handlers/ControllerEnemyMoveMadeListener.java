package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IEnemyMoveMadeListener;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.view.console.game_events.EnemyMoveMadeGameEvent;
import vermesa.lotr.view.console.game_events.GameEvent;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ControllerEnemyMoveMadeListener implements IEnemyMoveMadeListener {
    private final BlockingQueue<GameEvent> eventQueue;

    public ControllerEnemyMoveMadeListener(BlockingQueue<GameEvent> eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void listen(List<IAction> enemyMovesMade, boolean humanPlayersTurn) {
        eventQueue.add(new EnemyMoveMadeGameEvent(enemyMovesMade, humanPlayersTurn));
    }
}
