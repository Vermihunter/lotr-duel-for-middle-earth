package vermesa.lotr.model.utils;

import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.central_board.RegionType;

import java.util.List;

public class RegionUtils {
    public static Region findByType(List<Region> regions, RegionType type) {
        for (Region r : regions) {
            // if RegionType is an enum you can use ==, otherwise .equals()
            if (r.getRegionType() == type) {
                return r;
            }
        }
        return null;  // or throw, or Optional.empty(), if you prefer
    }
}
