package vermesa.lotr.model.game;

import vermesa.lotr.model.skills.SkillSet;
import vermesa.lotr.model.moves.ChapterCardDiscardMove;
import vermesa.lotr.model.moves.ChapterCardPlayMove;
import vermesa.lotr.model.moves.IMove;
import vermesa.lotr.model.moves.MoveResult;
import vermesa.lotr.model.player.Player;

import java.util.ArrayList;

public class Game {
    private final GameContext context;
    private final GameState state;

    public Game(GameContext context, GameState state) {
        this.context = context;
        this.state = state;
    }

    public ArrayList<IMove> getPossibleMoves() {
        ArrayList<IMove> possibleMoves = new ArrayList<>();

        addChapterCardMoves(possibleMoves);
        addLandmarkTileMoves(possibleMoves);

        return possibleMoves;
    }

    public MoveResult makeMove(IMove move) {
        MoveResult moveRes = move.playMove(context, state);


        return new MoveResult();
    }

    private void addChapterCardMoves(ArrayList<IMove> moves) {
        var playableChapterCards = state.getCurrentRoundInformation().getChapterCards().getPlayableChapterCards();
        Player playerOnMove = state.getPlayerOnMove();
        var playerSkills = playerOnMove.getSkills();
        int playerCoins = playerOnMove.getCoins();

        for (var playableChapterCard : playableChapterCards) {
            moves.add(new ChapterCardDiscardMove(playableChapterCard));

            // TODO: check if the given chapter card is playable or not for the given player
            SkillSet requiredSkills = playableChapterCard.context().requiredSkillSet();
            int coinsToPlayCard = playableChapterCard.context().coinsToPlay();


            int skillsMissing = playerSkills.missingSkills(requiredSkills);
            if (skillsMissing + coinsToPlayCard <= playerCoins) {
                moves.add(new ChapterCardPlayMove(playableChapterCard));
            }
        }
    }

    private void addLandmarkTileMoves(ArrayList<IMove> moves) {

    }

}
