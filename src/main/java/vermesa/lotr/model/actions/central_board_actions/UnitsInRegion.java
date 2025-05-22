package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.central_board.Region;

/**
 * Represents a number of units in context with a region
 *
 * @param region Region where the units are connected to
 * @param units  Number of units
 */
record UnitsInRegion(Region region, int units) {
}
