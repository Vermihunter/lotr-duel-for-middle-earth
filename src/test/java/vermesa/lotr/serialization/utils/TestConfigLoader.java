package vermesa.lotr.serialization.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.model.central_board.RegionTest;
import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.serialization.json.RegionMixIn;
import vermesa.lotr.serialization.json.players.FellowshipPlayerMixIn;
import vermesa.lotr.serialization.json.players.PlayerMixIn;
import vermesa.lotr.serialization.json.players.SauronPlayerMixIn;

import java.io.InputStream;
import java.util.List;

public class TestConfigLoader {
    public static List<RegionTest.AddUnitsTestConfig> loadAllConfigs(String resourcePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 1) Mix-In for polymorphic Player binding:
        mapper.addMixIn(Player.class, PlayerMixIn.class);
        mapper.addMixIn(SauronPlayer.class, SauronPlayerMixIn.class);
        mapper.addMixIn(FellowshipPlayer.class, FellowshipPlayerMixIn.class);

        // 2) (Optionally) register subtypes by name (for clarity):
        mapper.registerSubtypes(
                new NamedType(SauronPlayer.class, "SauronPlayer"),
                new NamedType(FellowshipPlayer.class, "FellowshipPlayer")
        );

        // 3) Mix-In for Region, so Jackson knows how to call your existing constructor:
        mapper.addMixIn(Region.class, RegionMixIn.class);

        // Now read the JSON array from the classpath (e.g. “/add_units_test_config.json”):
        try (InputStream is = TestConfigLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            return mapper.readValue(
                    is,
                    mapper.getTypeFactory()
                            .constructCollectionType(List.class, RegionTest.AddUnitsTestConfig.class)
            );
        }
    }
}

