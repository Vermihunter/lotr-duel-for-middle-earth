package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.coin_actions.GainExtraCoinsForDiscardedChapterCardAction;
import vermesa.lotr.view.console.annotations.Serializes;

import java.util.Arrays;
import java.util.stream.Collectors;

@Serializes(GainExtraCoinsForDiscardedChapterCardAction.class)
public class GainExtraCoinsForDiscardedChapterCardActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (GainExtraCoinsForDiscardedChapterCardAction) _move;

        String serializedCoins = Arrays.stream(move.extraCoinsPerRound())
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(",", "[", "]"));

        return "From now on receive the following amount of extra coins for discarding a chapter card: " + serializedCoins;
    }
}
