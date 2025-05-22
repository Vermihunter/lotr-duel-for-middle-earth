package vermesa.lotr.view.console.game_events;

import vermesa.lotr.model.actions.IAction;

import java.util.List;

public record EnemyMoveMadeGameEvent(List<IAction> move) implements GameEvent {
}
