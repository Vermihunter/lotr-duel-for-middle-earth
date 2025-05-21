package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.chapter_cards.RoundChapterCardSet;

import java.util.AbstractMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChapterCardSetSerializer {
    private static String cardSeparator = "----";
    private static String faceDownCard = "|XX|";
    private static String emptySpace = "    ";
    private static String secondRowStart = "|";

    public static String serialize(RoundChapterCardSet s) {
        var allCards = s.getAllChapterCards();
        // Get the dimensions of the rows -> ROW x COLUMN
        Map<Integer, Long> counts = allCards.values().stream()
                .collect(Collectors.groupingBy(
                        RoundChapterCardSet.ChapterCardWrapper::getRow,    // classifier function
                        TreeMap::new,
                        Collectors.counting() // downstream collector
                ));

        // Get the max entry to know the width of the widest row
        Map.Entry<Integer, Long> maxEntry = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>(0, 0L));


        // There is a space between cards to show correlation for previous rows
        int width = (int) (2 * maxEntry.getValue() - 1);
        StringBuilder sb = new StringBuilder();

        int currID = 0;
        for (var count : counts.entrySet()) {
            long _columns = count.getValue();
            int columns = (int) (_columns);

            // Left alignment (in card width)
            int spacesLeft = (width - (2 * columns - 1)) / 2;


            String[] firstRow = new String[columns];
            String[] secondRow = new String[columns];
            String[] thirdRow = new String[columns];

            // Construct chapter card rows
            for (int i = 0; i < columns; i++, currID++) {
                var cardWrapper = allCards.get(currID);

                // Don't show already played cards
                if (cardWrapper.isAlreadyPlayed()) {
                    firstRow[i] = emptySpace;
                    secondRow[i] = emptySpace;
                    thirdRow[i] = emptySpace;
                    continue;
                }

                firstRow[i] = cardSeparator;
                thirdRow[i] = cardSeparator;

                // If a card is face down, cannot show its ID
                if (!cardWrapper.isFaceUp()) {
                    secondRow[i] = faceDownCard;
                }
                // Else show the ID of the number, later display the meanings
                else {
                    String strID = String.format("%02d", currID + 1);

                    secondRow[i] = secondRowStart + strID + "|";
                }
            }

            // Actually print rows
            add(spacesLeft, columns, sb, emptySpace, firstRow);
            add(spacesLeft, columns, sb, emptySpace, secondRow);
            add(spacesLeft, columns, sb, emptySpace, thirdRow);
        }

        return sb.toString();
    }

    /**
     * Adds a row of chapter cards to the string builder
     *
     * @param spacesLeft Left alignment
     * @param columns    Number of columns in the given row
     * @param sb         StringBuilder to append the rows to
     * @param emptySpace Cached representation of the empty spaces
     * @param s          Array of strings to print, length should equal to `columns`
     */
    private static void add(int spacesLeft, int columns, StringBuilder sb, String emptySpace, String[] s) {
        sb.append(String.valueOf(emptySpace).repeat(Math.max(0, spacesLeft)));

        for (int i = 0; i < columns; i++) {
            sb.append(s[i]);
            sb.append(emptySpace);
        }
        sb.append("\n");
    }
}
