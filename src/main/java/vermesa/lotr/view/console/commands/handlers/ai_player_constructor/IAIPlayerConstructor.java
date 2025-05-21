package vermesa.lotr.view.console.commands.handlers.ai_player_constructor;

import vermesa.lotr.ai_players.IAIPlayer;

import java.util.Random;

public interface IAIPlayerConstructor {
    IAIPlayer constructPlayer(String[] args, Random rand);

    String getName();
}
