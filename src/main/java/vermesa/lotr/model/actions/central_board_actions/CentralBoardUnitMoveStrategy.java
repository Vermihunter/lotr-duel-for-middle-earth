package vermesa.lotr.model.actions.central_board_actions;

/**
 * Defines policies how unit may be moved on the central board
 * Note that in this case, the units are already on the board
 */
public enum CentralBoardUnitMoveStrategy {
    ANYWHERE,
    NEIGHBORING
}
