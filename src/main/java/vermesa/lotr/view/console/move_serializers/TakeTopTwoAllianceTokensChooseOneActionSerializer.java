package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.race_effect_actions.TakeTopTwoAllianceTokensChooseOneAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.Arrays;
import java.util.stream.Collectors;

@Serializes(TakeTopTwoAllianceTokensChooseOneAction.class)
public class TakeTopTwoAllianceTokensChooseOneActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (TakeTopTwoAllianceTokensChooseOneAction) _move;
        String racesSerialized = Arrays.stream(move.RacesToRevealFrom())
                .map(Enum::toString)
                .collect(Collectors.joining(", ", "[", "]"));


        return "Choose any race from races " + racesSerialized + ", reveal " + move.TokensToReveal() + " and choose " + move.TokensToChoose() + "\n";
    }
}
