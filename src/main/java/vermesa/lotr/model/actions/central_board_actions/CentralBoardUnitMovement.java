package vermesa.lotr.model.actions.central_board_actions;

import vermesa.lotr.model.central_board.Region;

public record CentralBoardUnitMovement(Region from, Region to, int unitsToMove) {
}
