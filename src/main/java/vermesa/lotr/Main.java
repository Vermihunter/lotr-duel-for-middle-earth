package vermesa.lotr;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import vermesa.lotr.model.actions.IAction;
import vermesa.lotr.model.game.CurrentGameState;
import vermesa.lotr.serialization.json.JsonConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON from file
        JsonConfig config = objectMapper.readValue(new File("DefaultConfig.json"), JsonConfig.class);
        //System.out.println(config);
        Random rand = new Random(1);
        var game = config.createGame(rand);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //Scanner sc = new Scanner(System.in);
        //  int moveInd = sc.nextInt();

        //while(game.getState().getCurrentRoundNumber() == 1) {
        while (game.getState().getCurrentGameState() == CurrentGameState.HAS_NOT_ENDED) {
            var moveOptions = game.getPossibleMoves();
            ArrayList<IAction> actions = new ArrayList<>();
            for (var moveOptionGroup : moveOptions) {


                int moveInd = rand.nextInt(moveOptionGroup.size());
                var moveOption = moveOptionGroup.get(moveInd);

                actions.add(moveOption);
            }


            // System.out.println(move);
            System.out.println(objectMapper.writeValueAsString(actions) + ",");
            game.makeMove(actions);
        }


        System.out.println(game.getState().getCurrentRoundNumber());
    }
}