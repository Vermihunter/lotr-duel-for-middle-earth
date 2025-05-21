package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.central_board.CentralBoard;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.player.Player;

public class CentralBoardSerializer {
    private static int width = 59;

    private static String upperCardSeparator = "╔";
    private static String lowerCardSeparator = "╚══╝";
    private static String faceDownCard = "║XX║";
    private static String emptySpace = "    ";
    private static String secondRowStart = "║";


    public static String serialize(CentralBoard centralBoard) {

        StringBuilder stringBuilder = new StringBuilder();

        addTitleRow(stringBuilder);
        centralBoard.regions().forEach(region -> addRegion(stringBuilder, region));


        return stringBuilder.toString();
    }

    private static void addTitleRow(StringBuilder stringBuilder) {
        // Title upper row
        stringBuilder.append("╔");
        stringBuilder.append("═".repeat(width - 2));
        stringBuilder.append("╗\n");

        // Title
        stringBuilder.append("║");
        stringBuilder.append(" ".repeat((width - 2 - 13) / 2));
        stringBuilder.append("Central board");
        stringBuilder.append(" ".repeat((width - 2 - 13) / 2));
        stringBuilder.append("║\n");

        // Title lower row
        stringBuilder.append("╚");
        stringBuilder.append("═".repeat(width - 2));
        stringBuilder.append("╝\n");

    }

    private static void addRegion(StringBuilder stringBuilder, Region region) {
        Player unitHolder = region.getUnit();
        int unitCount = region.getUnitCount();

        Player fortressHolder = region.getFortress();
        String title = region.getRegionType().toString();
        int titleSize = title.length();

        int regionWidth = 20;
        stringBuilder.append("╔");
        stringBuilder.append("═".repeat(regionWidth - 2));
        stringBuilder.append("╗\n");

        // Title
        stringBuilder.append("║");
        stringBuilder.append(" ".repeat((regionWidth - 2 - titleSize) / 2));
        stringBuilder.append(title);
        stringBuilder.append(" ".repeat((regionWidth - 2 - titleSize + titleSize % 2) / 2));
        stringBuilder.append("║\n");

        // Title lower row
        stringBuilder.append("╚");
        stringBuilder.append("═".repeat(regionWidth - 2));
        stringBuilder.append("╝\n");


        // Attributes
        stringBuilder.append("║ Units: ");


        // Lower row
        stringBuilder.append("╚");
        stringBuilder.append("═".repeat(regionWidth - 2));
        stringBuilder.append("╝\n");
    }
}
