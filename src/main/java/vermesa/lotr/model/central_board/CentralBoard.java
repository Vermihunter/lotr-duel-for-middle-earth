package vermesa.lotr.model.central_board;

import java.util.ArrayList;
import java.util.Iterator;

public class CentralBoard {
    private final ArrayList<Region> regions;

    public CentralBoard(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public ArrayList<Region> getRegions() { return regions; }
}
