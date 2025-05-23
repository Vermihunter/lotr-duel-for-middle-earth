package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.RevealAllianceTokensAndChooseSomeAction;
import vermesa.lotr.model.race_effects.AllianceToken;
import vermesa.lotr.view.console.annotations.Serializes;
import vermesa.lotr.view.console.state_serializers.AllianceTokenSerializer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Serializes(RevealAllianceTokensAndChooseSomeAction.class)
public class RevealAllianceTokensAndChooseSomeActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (RevealAllianceTokensAndChooseSomeAction) _move;

        String revealAllianceTokensSerialized = serializeAllianceTokens(move.tokensToReveal());
        String usedAllianceTokensSerialized = serializeAllianceTokens(move.tokensToChoose());

        return "Revealed alliance tokens: \n" + revealAllianceTokensSerialized + "\t\tUsed alliance tokens" + usedAllianceTokensSerialized + "\n";
    }

    private String serializeAllianceTokens(AllianceToken[] allianceTokens) {
        return Arrays.stream(allianceTokens)
                .map(AllianceTokenSerializer::serialize)
                .collect(Collectors.joining("\t\t\t-"));
    }
}
