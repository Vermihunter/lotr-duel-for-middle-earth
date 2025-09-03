package vermesa.lotr;

import vermesa.lotr.model.game.*;

import java.util.Random;

@TestedGameStateCreatorInfo(
        state = TestedGameState.GAME_ENDED
)
public class GameEndedGameStateCreator implements TestedGameStateCreator {

    @Override
    public Game createGame() {
        var game = new ThirdRoundFirstMoveGameStateCreator().createGame();
        var state = game.state();

        var chapterCardSet = state.getCurrentRoundInformation().getChapterCards();
        var playableChapterCards = chapterCardSet.getPlayableChapterCards();
        while (!playableChapterCards.isEmpty()) {
            chapterCardSet.moveSuccessful(playableChapterCards.getFirst());
        }

        return game;
    }
}
