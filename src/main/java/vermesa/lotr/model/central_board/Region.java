package vermesa.lotr.model.central_board;

import vermesa.lotr.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Region on the Central board
 */
public class Region implements Serializable {
    /**
     * Type of the Region
     */
    private final RegionType regionType;

    /**
     * Name of the fortress of the Region
     */
    private final String fortressName;

    /**
     * List of the Regions that are connected to the current one and is possible
     * to move a unit to from this one
     */
    private final ArrayList<Region> connectedRegions;

    /**
     * The Player reference that has a fortress placed in this Region
     * If no player has a fortress in this region, the value is null
     */
    private Player fortress;

    /**
     * The Player reference that has unit(s) placed in this Region
     * If no player has units in this region, the value is null.
     * Note that according to the official rules, there cannot be units from
     * both players in the same Region at the same time
     */
    private Player unit;

    /**
     * The number of units that are currently placed in this region by player {@link #unit}
     */
    private int unitCount;


    public Region(RegionType regionType, String fortressName) {
        this.regionType = regionType;
        this.fortressName = fortressName;
        this.connectedRegions = new ArrayList<>();
        fortress = null;
        unit = null;
        unitCount = 0;
    }

    public List<Region> getConnectedRegions() {
        return connectedRegions;
    }

    public void addConnectedRegion(Region region) {
        connectedRegions.add(region);
    }

    /**
     * Adds unit(s) to the current Region for a given player
     * Instantly solves conflicts (if any) and modifies the {@link #unit} (if needed)
     *
     * @param player    The player who is placing units in this region
     * @param unitCount The number of units being placed
     */
    public void addUnits(Player player, int unitCount) {
        // There were no units in this region before or the same player already has units in this region
        if(unit == null || player == unit) {
            this.unitCount += unitCount;
            unit = player;
            return;
        }

        // The enemy has the same amount of units → after solving the conflicts no units remain
        if(unitCount == this.unitCount) {
            this.unitCount = 0;
            unit = null;
        }
        // The enemy had fewer units in this region than the current player is placing → current player gains control
        else if (unitCount > this.unitCount) {
            this.unitCount = unitCount - this.unitCount;
            unit = player;
        }
        // The enemy had more units in this region → control remains in the hand of the enemy
        else {
            this.unitCount -= unitCount;
        }
    }

    /**
     * Removes units from a given region
     * Note that at the same time only a single player can have units in a region
     * according to the rules so there is no need to define which player's units to remove
     *
     * @param unitCount The number of units to remove
     */
    public void removeUnits(int unitCount) {
        if (unitCount > this.unitCount || unitCount < 0) {
            throw new IllegalArgumentException();
        }

        this.unitCount -= unitCount;
        if(unitCount == this.unitCount) {
            this.unit = null;
        }
    }

    /** Setters and getters */

    public void removeFortress() {
        fortress = null;
    }

    public void placeFortress(Player player) {
        fortress = player;
    }
    public Player getFortress() { return fortress; }


    public Player getUnit() { return unit; }
    public int getUnitCount() { return unitCount; }

    public RegionType getRegionType() { return regionType; }
}
