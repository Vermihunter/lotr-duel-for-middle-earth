/**
 * This package defines how the user input through the console is processed.
 * It defines a recursive way of handling. Each {@link vermesa.lotr.view.console.commands.handlers.ICommandHandler}
 * processes the first couple of arguments that it needs and forwards the remaining ones for
 * further processing to the next.
 *
 * @author Ákos Vermes
 * @since 1.0
 */
package vermesa.lotr.view.console.commands.handlers;