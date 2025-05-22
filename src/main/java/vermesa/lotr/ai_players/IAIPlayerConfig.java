package vermesa.lotr.ai_players;

import vermesa.lotr.model.player.Player;

public interface IAIPlayerConfig {
    IAIPlayer constructAIPlayer(Player me);
}
