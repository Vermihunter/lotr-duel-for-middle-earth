package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.game.Game;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.view.console.grid.ConsoleComponent;
import vermesa.lotr.view.console.grid.ConsoleGrid;
import vermesa.lotr.view.console.state_serializers.ChapterCardSetSerializer.SerializedChapterCardSet;

import java.util.Arrays;

public class FullMapStateSerializer {
    // private static final int rows = 85;
    private static final int columns = 120;

    public static String serialize(Game game) {
        var chapterCardsSerialized = ChapterCardSetSerializer.serialize(game.getState().getCurrentRoundInformation().getChapterCards());


        var reserve = getCoinReserveComponent(game);
        var landmarks = getLandmarkComponent(game);
        var centralBoard = getCentralBoardComponent(game);
        var questOfTheRingTrack = getQuestOfTheRingTrackComponent(game);
        var chapterCards = getChapterCardsComponent(chapterCardsSerialized);
        var chapterCardsDescriptions = getChapterCardsDescriptionComponent(chapterCardsSerialized);
        var fellowshipPlayerState = getPlayerStateComponent(game.getContext().getFellowshipPlayer());
        var sauronPlayerState = getPlayerStateComponent(game.getContext().getSauronPlayer());

        var components = new ConsoleComponent[]{
                reserve, landmarks, centralBoard, questOfTheRingTrack, chapterCards,
                chapterCardsDescriptions, fellowshipPlayerState, sauronPlayerState
        };

        int rows = reserve.getLines().length + landmarks.getLines().length + centralBoard.getLines().length
                + questOfTheRingTrack.getLines().length + chapterCards.getLines().length
                + chapterCardsDescriptions.getLines().length;

        ConsoleGrid grid = new ConsoleGrid(rows, columns);
        grid.addAbsolute(reserve, 0, 0);
        grid.placeBelow(reserve, landmarks, 0);
        grid.placeBelow(landmarks, centralBoard, 0);
        grid.placeBelow(centralBoard, questOfTheRingTrack, 0);

        grid.placeBelow(questOfTheRingTrack, chapterCards, 0);
        grid.placeBelow(chapterCards, chapterCardsDescriptions, 0);

        grid.placeLeft(centralBoard, fellowshipPlayerState, 1);
        grid.addAbsolute(sauronPlayerState, 23 + 1 + 72 + 1, centralBoard.getY());

        return grid.render();
    }

    private static ConsoleComponent getPlayerStateComponent(Player player) {
        String playerStateSerialized = PlayerStateSerializer.serialize(player);
        String[] lines = playerStateSerialized.split("\n");

        return new ConsoleComponent(lines, lines[0].length(), 0);
    }

    private static ConsoleComponent getChapterCardsDescriptionComponent(SerializedChapterCardSet chapterCardsSerialized) {
        String[] lines = chapterCardsSerialized.cardDescriptions.split("\n");

        int width = Arrays.stream(lines)
                .mapToInt(String::length)        // map each to its length
                .max()                           // find the max
                .orElse(0);                      // default 0 if empty

        return new ConsoleComponent(lines, width, 30);
    }

    private static ConsoleComponent getChapterCardsComponent(SerializedChapterCardSet chapterCardsSerialized) {
        String[] lines = chapterCardsSerialized.titleAndCards.split("\n");

        int width = lines[0].length();
        return new ConsoleComponent(lines, width, (columns - width) / 2);
    }

    private static ConsoleComponent getQuestOfTheRingTrackComponent(Game game) {
        String questOfTheRingTrackSerialized = QuestOfTheRingTrackSerializer.seralize(game.getContext().getQuestOfTheRingTrack());
        String[] lines = questOfTheRingTrackSerialized.split("\n");
        int width = lines[0].length();

        return new ConsoleComponent(lines, width, (columns - width) / 2);
    }

    private static ConsoleComponent getCentralBoardComponent(Game game) {
        String centralBoardSerialized = CentralBoardSerializer.serialize(game.getContext().getCentralBoard());
        String[] lines = centralBoardSerialized.split("\n");
        int width = lines[0].length();

        return new ConsoleComponent(lines, width, (columns - width) / 2);
    }

    private static ConsoleComponent getLandmarkComponent(Game game) {
        String landmarksSerialized = LandmarkTileSerializer.serialize(game.getState().getCurrentlyUsableLandmarkTiles());
        String[] lines = landmarksSerialized.split("\n");
        int width = lines[0].length();

        return new ConsoleComponent(lines, width, (columns - width) / 2);
    }

    private static ConsoleComponent getCoinReserveComponent(Game game) {
        String reserveSerialized = CoinsSerializer.serialize("Coins in reserve", game.getState().getTotalCoins());
        String[] lines = reserveSerialized.split("\n");
        int width = lines[0].length();

        return new ConsoleComponent(lines, width, (columns - width) / 2);

    }
}

