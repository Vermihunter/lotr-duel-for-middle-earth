package vermesa.lotr.view.console.commands.handlers.ai_player_constructor;

import vermesa.lotr.ai_players.IAIPlayer;
import vermesa.lotr.ai_players.RandomAIPlayer;

import java.util.Random;

public class RandomAIPlayerConstructor implements IAIPlayerConstructor {
    @Override
    public IAIPlayer constructPlayer(String[] args, Random rand) {
        return new RandomAIPlayer(rand);
    }

    @Override
    public String getName() {
        return "random";
    }
}
