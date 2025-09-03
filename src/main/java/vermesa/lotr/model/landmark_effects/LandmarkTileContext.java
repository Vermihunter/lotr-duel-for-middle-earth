package vermesa.lotr.model.landmark_effects;

import java.io.Serializable;

/**
 * Represents the common context that is valid for every Landmark tile
 *
 * @param coinPerAlreadyPlacedFortressPawn The number of coins that has to be paid for every already placed fortress on the central board
 * @param landmarkTilesAtTime              The maximum number of landmark tiles that can be present at any given time
 */
public record LandmarkTileContext(int coinPerAlreadyPlacedFortressPawn,
                                  int landmarkTilesAtTime) implements Serializable {
}
