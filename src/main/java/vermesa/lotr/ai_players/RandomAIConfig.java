package vermesa.lotr.ai_players;

import vermesa.lotr.model.player.Player;

import java.util.Random;

public class RandomAIConfig implements IAIPlayerConfig {
    private final Random random;

    public RandomAIConfig(Random random) {
        this.random = random;
    }

    @Override
    public IAIPlayer constructAIPlayer(Player me) {
        return new RandomAIPlayer(random, me);
    }
}
