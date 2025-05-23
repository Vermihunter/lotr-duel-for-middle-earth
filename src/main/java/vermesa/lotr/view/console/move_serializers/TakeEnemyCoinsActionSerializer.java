package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.coin_actions.TakeEnemyCoinsAction;
import vermesa.lotr.view.console.annotations.Serializes;

@Serializes(TakeEnemyCoinsAction.class)
public class TakeEnemyCoinsActionSerializer implements IActionSerializer {
    @Override
    public String serialize(IAction _move) {
        var move = (TakeEnemyCoinsAction) _move;

        return "Take " + move.coinsToTake() + " coins from the enemy and put it back to the reserve\n";
    }
}
