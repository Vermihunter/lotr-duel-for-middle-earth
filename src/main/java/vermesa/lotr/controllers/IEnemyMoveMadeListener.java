package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;

import java.util.List;

public interface IEnemyMoveMadeListener {
    void listen(List<IAction> enemyMovesMade);
}
