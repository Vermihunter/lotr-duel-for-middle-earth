package vermesa.lotr.view.console.commands.handlers.game_state_handlers;

import vermesa.lotr.model.game.Game;
import vermesa.lotr.view.console.Context;
import vermesa.lotr.view.console.commands.handlers.CommandHandler;

public abstract class GameStateHandler extends CommandHandler {
    protected final Game game;

    public GameStateHandler(Context context, String name, String description, Game game) {
        super(context, name, description);
        this.game = game;
    }
}
