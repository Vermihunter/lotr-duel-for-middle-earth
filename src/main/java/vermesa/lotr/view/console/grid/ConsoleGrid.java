package vermesa.lotr.view.console.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages a grid buffer and places components in it.
 */
public class ConsoleGrid {
    private final int rows;
    private final int cols;
    private final char[][] buffer;
    private final List<ConsoleComponent> components = new ArrayList<>();

    public ConsoleGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.buffer = new char[rows][cols];
        clearBuffer();
    }

    private void clearBuffer() {
        for (int r = 0; r < rows; r++) {
            Arrays.fill(buffer[r], ' ');
        }
    }

    /**
     * Place component at absolute grid coordinates (x=column, y=row).
     */
    public void addAbsolute(ConsoleComponent comp, int x, int y) {
        comp.setPosition(x, y);
        components.add(comp);
    }

    /**
     * Place compB below compA by offset rows (relative to compA's Y).
     */
    public void placeBelow(ConsoleComponent compA, ConsoleComponent compB, int rowOffset) {
        int newY = compA.getY() + compA.getHeight() + rowOffset;
        int newX = compA.getX();
        addAbsolute(compB, newX, newY);
    }

    /**
     * Place compB to the right of compA by offset columns (relative to compA's X + width).
     */
    public void placeRight(ConsoleComponent compA, ConsoleComponent compB, int colOffset) {
        int newX = compA.getX() + compA.getWidth() + colOffset;
        int newY = compA.getY();
        addAbsolute(compB, newX, newY);
    }

    /**
     * Render all components into the buffer and return the composed string.
     */
    public String render() {
        clearBuffer();
        for (ConsoleComponent comp : components) {
            String[] lines = comp.getLines();
            int w = comp.getWidth();
            int px = comp.getX();
            int py = comp.getY();
            int indent = comp.getIndent();

            for (int r = 0; r < lines.length; r++) {
                if (py + r >= rows) break;
                String text = lines[r];
                // apply indent and width
                String line = applyIndent(text, indent, w);
                for (int c = 0; c < line.length() && c < cols - px; c++) {
                    if (px + c >= cols) break;
                    buffer[py + r][px + c] = line.charAt(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char[] row : buffer) {
            sb.append(row).append('\n');
        }
        return sb.toString();
    }

    private String applyIndent(String text, int indent, int width) {
        String prefix = repeat(' ', indent);
        String content = text.length() > width ? text.substring(0, width) : text;
        int pad = width - content.length();
        return prefix + content + repeat(' ', pad);
    }

    private String repeat(char ch, int count) {
        char[] arr = new char[count];
        Arrays.fill(arr, ch);
        return new String(arr);
    }
}
