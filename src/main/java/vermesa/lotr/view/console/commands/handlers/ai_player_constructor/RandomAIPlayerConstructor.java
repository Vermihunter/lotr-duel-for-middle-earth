package vermesa.lotr.view.console.commands.handlers.ai_player_constructor;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.ai_players.IAIPlayerConfig;
import vermesa.lotr.ai_players.RandomAIConfig;
import vermesa.lotr.ai_players.RandomAIPlayer;

import java.util.Random;

public class RandomAIPlayerConstructor implements IAIPlayerConstructor {
    @Override
    public IAIPlayerConfig constructPlayerConfig(String[] args, Random rand) {
        return new RandomAIConfig(rand);
    }

    @Override
    public String getName() {
        return "random";
    }
}
