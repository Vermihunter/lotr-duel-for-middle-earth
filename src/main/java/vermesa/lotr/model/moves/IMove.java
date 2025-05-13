package vermesa.lotr.model.moves;

import vermesa.lotr.model.actions.IAction;

public interface IMove extends IAction {
    default int coinsToPlay() {
        return 0;
    }
}
