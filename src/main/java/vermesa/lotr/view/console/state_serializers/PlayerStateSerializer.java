package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.chapter_cards.ChainingSymbols;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.race_effects.Race;
import vermesa.lotr.model.skills.Skill;
import vermesa.lotr.model.skills.SkillSet;

import java.util.Arrays;

public class PlayerStateSerializer {
    private static final int width = 23;

    public static String serialize(Player player) {
        StringBuilder builder = new StringBuilder();
        String playerName = player.getName() + " player";
        int rowsAdded = 0;

        String middleSeparatorRow = "╠" + "═".repeat(width - 2) + "╣\n";

        // Upper separator
        builder.append("╔");
        builder.append("═".repeat(width - 2));
        builder.append("╗");
        builder.append('\n');

        // Title
        addLineMiddleAlign(builder, playerName);
        builder.append(middleSeparatorRow);

        String coinsString = " - Coins: " + player.getCoins();
        addLineLeftAlign(builder, coinsString);

        String unitsString = " - Units: " + player.getUnits();
        addLineLeftAlign(builder, unitsString);

        String fortressesString = " - Fortresses: " + player.getFortresses();
        addLineLeftAlign(builder, fortressesString);

        builder.append(middleSeparatorRow);

        String separator = "║" + " ".repeat(width - 2) + "║\n";


        var supportCounts = player.getSupportingRaces();
        //var supportCounts = new int[]{1,1,3,1,1,2,1}; // Testing
        long nonZeroSupports = Arrays.stream(supportCounts)
                .filter(i -> i > 0)
                .count();

        // Add support of race title only if there is at least one race support
        if (nonZeroSupports > 0) {
            // Title
            String supportOfRacesTitle = " Support of races";
            addLineMiddleAlign(builder, supportOfRacesTitle);
            builder.append(middleSeparatorRow);
            rowsAdded += 2;

            // Race supports
            for (int i = 0; i < supportCounts.length; ++i) {
                if (supportCounts[i] > 0) {
                    String raceSupportString = " - " + Race.values()[i].toString() + ": " + supportCounts[i];
                    addLineLeftAlign(builder, raceSupportString);
                    rowsAdded++;
                }
            }

            builder.append(middleSeparatorRow);
        }

        //var chainingSymbols = new HashSet<>(Arrays.asList(ChainingSymbols.values())); // Testing
        var chainingSymbols = player.getPlayerState().getChainingSymbols();
        // Add chaining symbol title only if there is at least one chaining symbol to print
        if (!chainingSymbols.isEmpty()) {
            // Add title
            String obtainedChainingSymbolsTitle = "Chaining symbols";
            addLineMiddleAlign(builder, obtainedChainingSymbolsTitle);
            builder.append(middleSeparatorRow);
            rowsAdded += 2;

            // Add chaining symbols
            for (var chainingSymbol : chainingSymbols) {
                String chainingSymbolString = " - " + chainingSymbol.toString().charAt(0) + chainingSymbol.toString().toLowerCase().substring(1);
                addLineLeftAlign(builder, chainingSymbolString);
                rowsAdded++;
            }

            builder.append(middleSeparatorRow);
        }

        var skills = player.getSkills();
        var allSkills = skills.getAllSkillSets();
        var fixedSkills = allSkills.stream().filter(SkillSet::isOptional).toList();

        if (!fixedSkills.isEmpty()) {
            SkillSet combinedFixedSkills = SkillSet.combine(fixedSkills);

            String skillsTitle = "Skills";
            addLineMiddleAlign(builder, skillsTitle);
            builder.append(middleSeparatorRow);
            rowsAdded += 2;

            //var skillCounts = new int[]{1, 1, 1, 1, 1}; // Testing
            var skillCounts = combinedFixedSkills.getSkillCounts();
            for (int i = 0; i < skillCounts.length; ++i) {
                if (skillCounts[i] > 0) {
                    String skillString = " - " + Skill.values()[i].toString() + ": " + skillCounts[i];
                    addLineLeftAlign(builder, skillString);
                    rowsAdded++;
                }
            }
        }

        // Empty lines if any
        if (rowsAdded < 19) {
            builder.append(separator.repeat(19 - rowsAdded));
        }

        // Lower separator
        builder.append("╚");
        builder.append("═".repeat(width - 2));
        builder.append("╝");
        builder.append('\n');

        return builder.toString();
    }

    private static void addLineMiddleAlign(StringBuilder builder, String line) {
        builder.append("║");
        builder.append(" ".repeat((width - line.length() - 1) / 2)); // + (coinsString.length() + 1) % 2)
        builder.append(line);
        builder.append(" ".repeat((width - line.length() - 2) / 2));
        builder.append("║\n");
    }

    private static void addLineLeftAlign(StringBuilder builder, String line) {
        builder.append("║");
        builder.append(line);
        builder.append(" ".repeat((width - line.length() - 2)));
        builder.append("║\n");
    }
}
