package vermesa.lotr.view.console.game_events;

import vermesa.lotr.model.actions.IAction;

import java.util.List;

/**
 * A game event representing the fact that the enemy player has chosen some moves
 * Note that we need this event to give feedback to user about the moves of choice
 * for the enemy players.
 *
 * @param moves            The moves that the enemy player has made
 * @param humanPlayersTurn Boolean variable stating whether after the execution of {@link #moves} its the human players turn to move or not
 */
public record EnemyMoveMadeGameEvent(List<IAction> moves, boolean humanPlayersTurn) implements GameEvent {
}
