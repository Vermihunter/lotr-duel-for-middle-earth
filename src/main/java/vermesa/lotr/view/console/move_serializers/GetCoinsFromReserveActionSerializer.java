package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.coin_actions.GetCoinsFromReserveAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(GetCoinsFromReserveAction.class)
public class GetCoinsFromReserveActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (GetCoinsFromReserveAction) _move;

        return "Get " + move.coinsToGet() + " coins from reserve\n";
    }
}
