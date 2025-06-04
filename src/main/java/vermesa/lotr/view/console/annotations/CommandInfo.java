package vermesa.lotr.view.console.annotations;

import vermesa.lotr.view.console.commands.AppState;

import java.lang.annotation.*;

/**
 * Instead of listing view‐classes, we list state‐identifiers (strings or enums).
 * At compile time, the processor will generate a registry mapping each state →
 * the CommandDefinitions that belong there.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    /**
     * The ResourceBundle key for this command’s display name.
     */
    String nameKey();

    /**
     * The ResourceBundle key for this command’s description/help text.
     */
    String descKey();

    /**
     * One or more state‐ids in which this command should appear.
     */
    AppState[] states() default {};

    /**
     * If true, this handler appears in all states.
     */
    boolean global() default false;
}
