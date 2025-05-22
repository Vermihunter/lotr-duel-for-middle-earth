package vermesa.lotr.controllers;

import vermesa.lotr.model.actions.IAction;

import java.util.List;

/**
 * Event listener for components
 */
public interface IEnemyMoveMadeListener {
    void listen(List<IAction> enemyMovesMade);
}
