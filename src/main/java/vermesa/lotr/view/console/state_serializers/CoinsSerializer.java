package vermesa.lotr.view.console.state_serializers;

public class CoinsSerializer {
    public static String serialize(String title, int coins) {
        String coinsString = Integer.toString(coins);
        int width = title.length() + 4;

        StringBuilder result = new StringBuilder();

        // Upper separator
        result.append("╔");
        result.append("═".repeat(width - 2));
        result.append("╗");
        // Title
        result.append('\n');
        result.append("║ ");
        result.append(title);
        result.append(" ║\n");

        // Coins
        result.append("║");
        result.append(" ".repeat((width - coinsString.length() - 1) / 2 )); // + (coinsString.length() + 1) % 2)
        result.append(coinsString);
        result.append(" ".repeat((width - coinsString.length() - 1) / 2));
        result.append("║\n");

        // Lower separator
        result.append("╚");
        result.append("═".repeat(width - 2));
        result.append("╝");
        result.append('\n');

        return result.toString();
    }
}
