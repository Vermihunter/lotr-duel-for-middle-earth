package vermesa.lotr.model.central_board;

import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;

import java.util.ArrayList;

public class DefaultRegionBuilder {
    public static ArrayList<Region> buildDefaultRegions() {
        Region mordor = new Region(RegionType.Mordor, "BaradDur");
        Region rohan = new Region(RegionType.Rohan, "HelmsDeep");
        Region gondor = new Region(RegionType.Gondor, "MinasTirith");
        Region enedwaith = new Region(RegionType.Enedwaith, "Isengard");
        Region rhovanion = new Region(RegionType.Rhovanion, "Erebor");
        Region arnor = new Region(RegionType.Arnor, "Bree");
        Region lindon = new Region(RegionType.Lindon, "GreyHavens");

        /* Mordor config */
        mordor.addConnectedRegion(rohan);
        mordor.addConnectedRegion(gondor);
        mordor.addUnits(PlayerType.Sauron, 2);

        /* Rohan config */
        rohan.addConnectedRegion(mordor);
        rohan.addConnectedRegion(gondor);
        rohan.addConnectedRegion(rhovanion);
        rohan.addConnectedRegion(enedwaith);

        /* Gondor config */
        gondor.addConnectedRegion(mordor);
        gondor.addConnectedRegion(rohan);
        gondor.addConnectedRegion(enedwaith);

        /* Enedwaith config */
        enedwaith.addConnectedRegion(rohan);
        enedwaith.addConnectedRegion(gondor);
        enedwaith.addConnectedRegion(arnor);
        enedwaith.addConnectedRegion(rhovanion);

        /* Rhovanion config */
        rhovanion.addConnectedRegion(rohan);
        rhovanion.addConnectedRegion(arnor);
        rhovanion.addConnectedRegion(enedwaith);

        /* Arnor config */
        arnor.addConnectedRegion(lindon);
        arnor.addConnectedRegion(rhovanion);
        arnor.addConnectedRegion(enedwaith);
        arnor.addUnits(PlayerType.Fellowship, 2);

        /* Lindon config */
        lindon.addConnectedRegion(arnor);


        var regions = new ArrayList<Region>();
        regions.add(mordor);
        regions.add(rohan);
        regions.add(gondor);
        regions.add(arnor);
        regions.add(rhovanion);
        regions.add(enedwaith);
        regions.add(lindon);

        return regions;
    }

}
