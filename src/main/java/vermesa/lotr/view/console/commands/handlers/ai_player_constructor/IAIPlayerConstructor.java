package vermesa.lotr.view.console.commands.handlers.ai_player_constructor;

import vermesa.lotr.ai_players.IAIPlayerConfig;

import java.util.Random;

public interface IAIPlayerConstructor {
    IAIPlayerConfig constructPlayerConfig(String[] args, Random rand);

    String getName();
}
