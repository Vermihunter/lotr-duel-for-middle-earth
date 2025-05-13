package vermesa.lotr;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.serialization.json.JsonConfig;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON from file
        JsonConfig config = objectMapper.readValue(new File("DefaultConfig.json"), JsonConfig.class);
        //System.out.println(config);
        var game = config.createGame();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //Scanner sc = new Scanner(System.in);
        //  int moveInd = sc.nextInt();

        Random rand = new Random(1);
        //while(game.getState().getCurrentRoundNumber() == 1) {
        while (game.getState().getCurrentGameState() == CurrentGameState.HAS_NOT_ENDED) {
            var moves = game.getPossibleMoves();
            int moveInd = rand.nextInt(moves.size());
            IAction move = moves.get(moveInd);
            // System.out.println(move);
            System.out.println(objectMapper.writeValueAsString(move) + ",");
            game.makeMove(move);
        }


        System.out.println(game.getState().getCurrentRoundNumber());
    }
}