package vermesa.lotr.serialization.json.actions;

import vermesa.lotr.model.GameContext;
import vermesa.lotr.model.GameState;
import vermesa.lotr.model.actions.ActionResult;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.actions.TakeEnemyCoinsAction;
import vermesa.lotr.model.central_board.Region;

import java.util.HashMap;

public class TakeEnemyCoinsActionConfig extends ActionConfig{
    public int CoinsToTake;

    @Override
    public IAction constructAction(HashMap<String, Region> regionMapper) {
        return new TakeEnemyCoinsAction(CoinsToTake);
    }
}
