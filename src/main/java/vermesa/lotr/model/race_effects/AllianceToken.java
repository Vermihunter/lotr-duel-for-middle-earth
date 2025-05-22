package vermesa.lotr.model.race_effects;

import vermesa.lotr.model.actions.IAction;

/**
 * Represents an AllianceToken in the game
 * <p>
 * The action is one of
 * - {@link vermesa.lotr.model.actions.race_effect_actions.OneTimeImmediateRaceEffect}
 * - {@link vermesa.lotr.model.actions.race_effect_actions.GainAttributeRaceEffect}
 * - {@link vermesa.lotr.model.actions.race_effect_actions.EventBasedRaceEffect}
 * </p>
 *
 * @param action Effect of the Alliance token
 */
public record AllianceToken(IAction action) {

}
