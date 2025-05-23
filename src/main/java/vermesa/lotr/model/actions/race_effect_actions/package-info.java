/**
 * Actions that are connected to {@link vermesa.lotr.model.race_effects.AllianceToken} and {@link vermesa.lotr.model.race_effects.Race}
 * <p>
 *     There are two types of approach using which the race effects are implemented:
 *     <ul>
 *         <li>Attributes - there are certain attributes that a player can acquire and then at the right moment, queried whether the player has it or not and handle the situation accordingly. To add a new attribute, add a new element to the {@link vermesa.lotr.model.actions.race_effect_actions.RaceEffectAttributes}</li>
 *         <li>Callback events - some race effects add a callback-like functionality to have some action happening after another action has happened. The main interface to implement is {@link vermesa.lotr.model.actions.race_effect_actions.RaceEffectCallbackEventHandler}</li>
 *     </ul>
 * </p>
 *
 * @author Ákos Vermes
 * @since 1.0
 */
package vermesa.lotr.model.actions.race_effect_actions;