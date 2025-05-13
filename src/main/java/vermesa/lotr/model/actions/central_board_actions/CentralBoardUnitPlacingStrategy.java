package vermesa.lotr.model.actions.central_board_actions;

/**
 * Definies policies how the units may be placed on the board
 * Note that in this case the units are not on the board yet and
 * there may be multiple units that are being placed to the board at the same time
 */
public enum CentralBoardUnitPlacingStrategy {
    /**
     * All units must be placed to the selected region
     */
    ALL_TO_SAME_REGION,

    /**
     * Every unit can be chosen where to be placed from the selected regions
     */
    EVERY_UNIT_FREELY
}
