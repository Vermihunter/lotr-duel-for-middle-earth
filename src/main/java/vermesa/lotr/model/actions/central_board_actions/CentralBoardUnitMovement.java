package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.central_board.Region;

/**
 * Represents a movement on the central board
 *
 * @param from        The region where the units are moved from
 * @param to          The region where the units are moved to
 * @param unitsToMove The number of units to move
 */
public record CentralBoardUnitMovement(Region from, Region to, int unitsToMove) {
}
