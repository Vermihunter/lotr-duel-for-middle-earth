package vermesa.lotr.view.console.game_events;

import vermesa.lotr.model.game.CurrentGameState;

public record GameEndedEvent(CurrentGameState finalState) implements GameEvent {
}
