package vermesa.lotr.view.console.event_handlers;

import vermesa.lotr.controllers.IGameHasEndedListener;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.view.console.game_events.GameEndedEvent;
import vermesa.lotr.view.console.game_events.GameEvent;

import java.util.concurrent.BlockingQueue;

public class GameHasEndedListener implements IGameHasEndedListener {
    private final BlockingQueue<GameEvent> eventQueue;

    public GameHasEndedListener(BlockingQueue<GameEvent> eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void listen(CurrentGameState finalState) {
        eventQueue.add(new GameEndedEvent(finalState));
    }
}
