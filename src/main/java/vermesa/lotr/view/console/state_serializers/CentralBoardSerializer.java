package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.central_board.CentralBoard;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.central_board.RegionType;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.PlayerType;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class CentralBoardSerializer {
    private static final int width = 72;
    private static final int regionWidth = 18;
    static final String upperSeparator = "╔" + "═".repeat(regionWidth - 2) + "╗";
    static final String middleRow = "╠" + "═".repeat(regionWidth - 2) + "╣";
    static final String lowerRow = "╚" + "═".repeat(regionWidth - 2) + "╝";
    private static final int spaces = 9;
    static final String emptySeparator = " ".repeat(spaces);
    static final String middleRowSeparator = "←" + "-".repeat(spaces - 2) + "→";

    public static String serialize(CentralBoard centralBoard) {

        StringBuilder stringBuilder = new StringBuilder();
        addTitleRow(stringBuilder);

        Map<RegionType, Region> regionsMap = centralBoard.regions().stream()
                .collect(toMap(Region::getRegionType, Function.identity()));

        addRegions(stringBuilder, new Region[]{
                regionsMap.get(RegionType.Lindon),
                regionsMap.get(RegionType.Arnor),
                regionsMap.get(RegionType.Rhovanion)
        }, true);

        addRegions(stringBuilder, new Region[]{
                null,
                regionsMap.get(RegionType.Enedwaith),
                regionsMap.get(RegionType.Rohan)
        }, true);

        addRegions(stringBuilder, new Region[]{
                null,
                regionsMap.get(RegionType.Gondor),
                regionsMap.get(RegionType.Mordor)
        }, false);

        return stringBuilder.toString();
    }

    /**
     * Adds the title row for the Central board
     *
     * @param stringBuilder Main string builder
     */
    private static void addTitleRow(StringBuilder stringBuilder) {
        // Title upper row
        stringBuilder.append("╔");
        stringBuilder.append("═".repeat(width - 2));
        stringBuilder.append("╗\n");

        // Title
        stringBuilder.append("║");
        stringBuilder.append(" ".repeat((width - 2 - 13) / 2));
        stringBuilder.append("Central board");
        stringBuilder.append(" ".repeat((width - 2 - 12) / 2));
        stringBuilder.append("║\n");

        // Title lower row
        stringBuilder.append("╚");
        stringBuilder.append("═".repeat(width - 2));
        stringBuilder.append("╝\n");

    }



    /**
     * Used as a generator for a row to print the title of a Region
     *
     * @param region Region to print the title for
     * @return String representation of the row
     */
    private static String getRegionTitleRow(Region region) {
        String title = region.getRegionType().toString();
        int titleSize = title.length();

        return "║" +
                " ".repeat((regionWidth - 2 - titleSize) / 2)
                + title
                + " ".repeat((regionWidth - 2 - titleSize + titleSize % 2) / 2)
                + "║";
    }

    /**
     * Adds the title row for a set of regions
     *
     * @param stringBuilder Main string builder
     * @param regions       Regions to generate for - the ones that are in the same row
     */
    private static void addRegionTitles(StringBuilder stringBuilder, Region[] regions) {
        StateSerializer.add(stringBuilder, regions, upperSeparator, emptySeparator, regionWidth);
        StateSerializer.addWithGenerator(stringBuilder, regions, CentralBoardSerializer::getRegionTitleRow, emptySeparator, regionWidth);
        StateSerializer.add(stringBuilder, regions, middleRow, middleRowSeparator, regionWidth);
    }


    /**
     * Used as a generator for a row to print information about the units in a Region
     *
     * @param region Region to print the units for
     * @return String representation of the row
     */
    private static String getRegionUnitsRow(Region region) {
        PlayerType unitHolder = region.getUnit();
        int unitCount = region.getUnitCount();

        String unitsString = (unitHolder == null)
                ? "0"
                : unitCount + "(" + unitHolder.name().charAt(0) + ")";

        // Attributes
        return "║ Units: " +
                unitsString +
                " ".repeat(regionWidth - 2 - 8 - unitsString.length()) +
                "║";
    }

    /**
     * Used as a generator for a row to print information about the Fortress in a Region
     *
     * @param region Region to print the fortress for
     * @return String representation of the row
     */
    private static String getRegionFortressRow(Region region) {
        PlayerType fortressHolder = region.getFortress();
        char fortressChar = (fortressHolder == null) ? 'X' : fortressHolder.name().charAt(0);

        return "║ Fortress: " +
                fortressChar +
                " ".repeat(regionWidth - 2 - 11 - 1) +
                "║";
    }

    /**
     * Adds information about a set of regions - namely the units and the fortresses
     *
     * @param stringBuilder Main string builder
     * @param regions       Regions to generate for i.e. the ones that are in the same row
     */
    private static void addAttributes(StringBuilder stringBuilder, Region[] regions) {

        StateSerializer.addWithGenerator(stringBuilder, regions, CentralBoardSerializer::getRegionUnitsRow, emptySeparator, regionWidth);
        StateSerializer.addWithGenerator(stringBuilder, regions, CentralBoardSerializer::getRegionFortressRow, emptySeparator, regionWidth);
        StateSerializer.add(stringBuilder, regions, lowerRow, emptySeparator, regionWidth);
    }

    /**
     * Adds regions to the same row
     *
     * @param stringBuilder Main string builder
     * @param regions       Regions to be in the same row
     */
    private static void addRegions(StringBuilder stringBuilder, Region[] regions, boolean addDependencies) {
        addRegionTitles(stringBuilder, regions);
        addAttributes(stringBuilder, regions);

        if (addDependencies) {
            stringBuilder.append("                                     ↑             ↗           ↑\n");
            stringBuilder.append("                                     |          ╱              |\n");
            stringBuilder.append("                                     ↓        ↙                ↓\n");
        }

    }
}
