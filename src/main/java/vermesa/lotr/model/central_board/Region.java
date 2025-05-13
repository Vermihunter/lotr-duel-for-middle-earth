package vermesa.lotr.model.central_board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;


public class Region {
    private final RegionType regionType;
    private final String fortressName;
    @JsonIgnore
    private final ArrayList<Region> connectedRegions;
    private Player fortress;
    private Player unit;
    private int unitCount;


    public Region(RegionType regionType, String fortressName) {
        this.regionType = regionType;
        this.fortressName = fortressName;
        this.connectedRegions = new ArrayList<Region>();
        fortress = null;
        unit = null;
        unitCount = 0;
    }

    public void addConnectedRegion(Region region) {
        connectedRegions.add(region);
    }

    public void addUnits(Player player, int unitCount) {
        if(unit == null || player == unit) {
            this.unitCount += unitCount;
            unit = player;
            return;
        }

        if(unitCount == this.unitCount) {
            this.unitCount = 0;
            unit = null;
        } else if(unitCount > this.unitCount) {
            this.unitCount = unitCount - this.unitCount;
            unit = player;
        } else {
            this.unitCount -= unitCount;
        }
    }

    /**
     * Removes units from a given region
     * Note that at the same time only a single player can have units in a region
     * according to the rules so there is no need to define which player's units to remove
     *
     * @param unitCount
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
