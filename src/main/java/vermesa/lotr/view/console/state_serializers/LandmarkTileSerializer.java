package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.landmark_effects.LandmarkTile;
import vermesa.lotr.model.skills.Skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LandmarkTileSerializer {
    private static final int width = 22;
    private static final int spaces = 3;
    private static final String titleUpperRow = "╔" + "═".repeat(width - 2) + "╗";
    private static final String emptySeparator = " ".repeat(spaces);
    private static final String landmarkTitleRow = "║" + " ".repeat((width - 2 - 13) / 2) + "Landmark Tile" + " ".repeat((width - 2 - 12) / 2) + "║";
    private static final String titleLowerRow = "╠" + "═".repeat(width - 2) + "╣";
    private static final String requiredSkillsRow = "║ Required skills:" + " ".repeat(width - 19) + "║";
    private static final String lowerRow = "╚" + "═".repeat(width - 2) + "╝";

    public static String serialize(List<LandmarkTile> landmarkTiles) {
        LandmarkTile[] landmarkTileArray = landmarkTiles.toArray(new LandmarkTile[0]);

        StringBuilder builder = new StringBuilder();

        addTitle(builder, landmarkTileArray);
        addAttributes(builder, landmarkTileArray, landmarkTiles);

        return builder.toString();
    }

    private static void addTitle(StringBuilder stringBuilder, LandmarkTile[] landmarkTiles) {
        StateSerializer.add(stringBuilder, landmarkTiles, titleUpperRow, emptySeparator, width);
        StateSerializer.add(stringBuilder, landmarkTiles, landmarkTitleRow, emptySeparator, width);
        StateSerializer.add(stringBuilder, landmarkTiles, titleLowerRow, emptySeparator, width);
    }

    private static String getLandmarkTileRegionNameRow(LandmarkTile landmarkTile) {
        String regionName = landmarkTile.region().getRegionType().toString();

        return "║ Region: " +
                regionName +
                " ".repeat(width - regionName.length() - 10 - 1) +
                "║";
    }

    public static String getSkill(LandmarkTile landmarkTile, List<String> skills, int ind) {
        if (skills.size() <= ind) {
            return "║" +
                    " ".repeat(width - 2) +
                    "║";

        }

        String skillString = skills.get(ind);
        return "║ - " +
                skillString +
                " ".repeat(width - skillString.length() - 5) +
                "║";
    }

    private static void addAttributes(StringBuilder builder, LandmarkTile[] landmarkTileArray, List<LandmarkTile> landmarkTiles) {
        StateSerializer.addWithGenerator(builder, landmarkTileArray, LandmarkTileSerializer::getLandmarkTileRegionNameRow, emptySeparator, width);
        StateSerializer.add(builder, landmarkTileArray, requiredSkillsRow, emptySeparator, width);

        var skillStrings = collectStringSkills(landmarkTileArray);
        int maxSize = skillStrings.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);


        for (int i = 0; i < maxSize; i++) {
            int finalI = i;
            StateSerializer.addWithGenerator(builder, landmarkTileArray, (landmarkTile) -> {
                int landmarkInd = landmarkTiles.indexOf(landmarkTile);

                return LandmarkTileSerializer.getSkill(
                        landmarkTile,
                        skillStrings.get(landmarkInd),
                        finalI
                );
            }, emptySeparator, width);
        }

        StateSerializer.add(builder, landmarkTileArray, lowerRow, emptySeparator, width);
    }

    private static List<List<String>> collectStringSkills(LandmarkTile[] landmarkTileArray) {
        return Arrays.stream(landmarkTileArray)
                .map(landmarkTile -> {
                    int[] counts = landmarkTile.requiredSkillset().getSkillCounts();
                    return Arrays.stream(Skill.values())
                            .filter(skill -> counts[skill.ordinal()] > 0)
                            .map(skill -> skill + ": " + counts[skill.ordinal()])
                            .collect(toList());
                })
                .collect(toList());
    }

}
