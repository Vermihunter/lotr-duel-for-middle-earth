package vermesa.lotr.view.console.utils;

import vermesa.lotr.view.console.Context;

public class BoxPrinter {

    /**
     * Prints multiple sections in one box. Each section's lines are
     * centered, and sections are divided by a ╠═…═╣ row.
     *
     * @param sections array of Section objects
     * @param width    total width of the box (including borders)
     */
    public static void printSections(Section[] sections, int width, Context context) {
        if (sections == null || sections.length == 0) {
            printSingle("", width, context);
            return;
        }

        int inner = width - 2;
        // Validate every line across all sections fits
        for (Section sec : sections) {
            for (String line : sec.getLines()) {
                int len = (line == null ? 0 : line.length());
                if (len > inner) {
                    throw new IllegalArgumentException(
                            "Width too small for line \"" + line + "\": needs at least " + (len + 2)
                    );
                }
            }
        }

        // Top border
        context.out.print("╔");
        context.out.print("═".repeat(inner));
        context.out.println("╗");

        // Iterate sections
        for (int s = 0; s < sections.length; s++) {
            String[] lines = sections[s].getLines();
            // Print each line centered
            for (String line : lines) {
                String text = line == null ? "" : line;
                int left = (inner - text.length()) / 2;
                int right = inner - text.length() - left;
                context.out.print("║");
                context.out.print(" ".repeat(left));
                context.out.print(text);
                context.out.print(" ".repeat(right));
                context.out.println("║");
            }

            // Between sections, draw a section separator
            if (s < sections.length - 1) {
                context.out.print("╠");
                context.out.print("═".repeat(inner));
                context.out.println("╣");
            }
        }

        // Bottom border
        context.out.print("╚");
        context.out.print("═".repeat(inner));
        context.out.println("╝");
    }

    // Fallback single-line box (if no sections)
    private static void printSingle(String content, int width, Context context) {
        printBox(content, width, context);
    }

    /**
     * Prints a boxed, centered message to stdout.
     *
     * @param content the text to center in the box (must be a single line)
     * @param width   the total width of the box, including borders
     * @throws IllegalArgumentException if width is too small to contain content + padding
     */
    public static void printBox(String content, int width, Context context) {
        if (content == null) {
            content = "";
        }
        int innerWidth = width - 2;
        if (innerWidth < content.length()) {
            throw new IllegalArgumentException(
                    "Width too small: need at least " + (content.length() + 2) + " but got " + width
            );
        }

        // Top border
        context.out.print("╔");
        context.out.print("═".repeat(innerWidth));
        context.out.println("╗");

        // Content row
        int leftPadding = (innerWidth - content.length()) / 2;
        int rightPadding = innerWidth - content.length() - leftPadding;

        context.out.print("║");
        context.out.print(" ".repeat(leftPadding));
        context.out.print(content);
        context.out.print(" ".repeat(rightPadding));
        context.out.println("║");

        // Bottom border
        context.out.print("╚");
        context.out.print("═".repeat(innerWidth));
        context.out.println("╝");
    }

    /**
     * Prints a boxed list of messages, centering each line and
     * separating them with a ╠═...═╣ row.
     *
     * @param contents array of lines to print inside the box
     * @param width    total width of the box, including borders
     */
    public static void printBox(String[] contents, int width, Context context) {
        if (contents == null || contents.length == 0) {
            printBox("", width, context);
            return;
        }

        int inner = width - 2;
        // Validate each line fits
        for (String line : contents) {
            int len = (line == null ? 0 : line.length());
            if (len > inner) {
                throw new IllegalArgumentException(
                        "Width too small for line \"" + line + "\": needs at least " + (len + 2)
                );
            }
        }

        // Top border
        context.out.print("╔");
        context.out.print("═".repeat(inner));
        context.out.println("╗");

        // Content rows with separators
        for (int i = 0; i < contents.length; i++) {
            String line = contents[i] == null ? "" : contents[i];
            int left = (inner - line.length()) / 2;
            int right = inner - line.length() - left;

            // print content row
            context.out.print("║");
            context.out.print(" ".repeat(left));
            context.out.print(line);
            context.out.print(" ".repeat(right));
            context.out.println("║");

            // if not last, print separator row
            if (i < contents.length - 1) {
                context.out.print("╠");
                context.out.print("═".repeat(inner));
                context.out.println("╣");
            }
        }

        // Bottom border
        context.out.print("╚");
        context.out.print("═".repeat(inner));
        context.out.println("╝");
    }

    /**
     * Represents a contiguous block of lines to print together.
     */
    public static class Section {
        private final String[] lines;

        public Section(String... lines) {
            this.lines = lines == null ? new String[0] : lines;
        }

        public String[] getLines() {
            return lines;
        }
    }
}