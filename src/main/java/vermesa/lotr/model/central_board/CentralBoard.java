package vermesa.lotr.model.central_board;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the Central board that is a collection of regions
 *
 * @param regions The regions present on the central board
 */
public record CentralBoard(ArrayList<Region> regions) {
}
