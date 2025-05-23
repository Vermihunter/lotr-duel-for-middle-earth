package vermesa.lotr.controllers;

import vermesa.lotr.model.game.CurrentGameState;

public interface IGameHasEndedListener {
    void listen(CurrentGameState finalState);
}
