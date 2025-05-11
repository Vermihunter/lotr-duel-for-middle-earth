package vermesa.lotr;

import com.fasterxml.jackson.databind.ObjectMapper;
import vermesa.lotr.serialization.json.JsonConfig;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON from file
        JsonConfig config = objectMapper.readValue(new File("DefaultConfig.json"), JsonConfig.class);
        System.out.println(config);
        var game = config.createGame();
    }
}