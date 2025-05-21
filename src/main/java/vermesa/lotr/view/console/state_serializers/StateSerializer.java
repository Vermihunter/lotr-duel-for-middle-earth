package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.landmark_effects.LandmarkTile;

import java.util.List;
import java.util.function.Function;

public class StateSerializer {

    /**
     * Helper to keep code clean - for static content
     *
     * @param stringBuilder StringBuilder
     * @param objects       Objects to add
     * @param s             String to print
     * @param separator     Length should be `spaces`
     * @param width         Width of the row
     */
    public static <T> void add(StringBuilder stringBuilder, T[] objects, String s, String separator, int width) {
        for (T object : objects) {
            if (object == null) {
                stringBuilder.append(" ".repeat(width + separator.length()));
                continue;
            }

            stringBuilder.append(s);
            stringBuilder.append(separator);
        }

        stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
        stringBuilder.append("\n");
    }

    public static <T> void addWithGenerator(StringBuilder stringBuilder, T[] objects, Function<T, String> generator, String separator, int width) {
        for (T object : objects) {
            if (object == null) {
                stringBuilder.append(" ".repeat(width + separator.length()));
                continue;
            }

            stringBuilder.append(generator.apply(object));
            stringBuilder.append(separator);
        }

        stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
        stringBuilder.append("\n");
    }
}
