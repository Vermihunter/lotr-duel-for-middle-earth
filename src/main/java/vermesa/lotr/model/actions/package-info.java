/**
 * <p>
 * This package represents the basic actions that can be executed in the game.
 * Note that according to the rules of the game, it is not worth it to implement
 * separately the functionalities of chapter cards and other cards since they implement
 * the same actions in many different areas that would cause overusing and hard coding
 * static methods or repeating ourselves too many times.
 * </p>
 * <p>
 * For this reason, the main interface {@link vermesa.lotr.model.actions.IAction} represents an action
 * that modifies the state of the game through the {@link vermesa.lotr.model.actions.IAction#action(vermesa.lotr.model.game.GameContext, vermesa.lotr.model.game.GameState)}
 * function.
 * </p>
 * The main difference between a {@link vermesa.lotr.model.actions.IAction} and a {@link vermesa.lotr.model.moves.IMove} is that
 * the {@link vermesa.lotr.model.moves.IMove} objects are sent to the players to choose from. Both of these interfaces
 * implement a similar functionality (namely to change the state of the game).
 *
 * @author Ákos Vermes
 * @since 1.0
 */
package vermesa.lotr.model.actions;