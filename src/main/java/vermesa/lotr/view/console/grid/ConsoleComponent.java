package vermesa.lotr.view.console.grid;


/**
 * Represents a block of text to be rendered in the console with a left indent.
 */
public class ConsoleComponent {
    private final String[] lines;
    private final int width;
    private final int indent;  // number of spaces to add to the left
    private int x; // column position (relative to grid origin)
    private int y; // row position (relative to grid origin)

    public ConsoleComponent(String[] lines, int width, int indent) {
        this.lines = lines;
        this.width = width;
        this.indent = indent;
    }

    public String[] getLines() {
        return lines;
    }

    public int getWidth() {
        return width;
    }

    public int getIndent() {
        return indent;
    }

    public int getHeight() {
        return lines.length;
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
