package vermesa.lotr.model.central_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import vermesa.lotr.model.player.FellowshipPlayer;
import vermesa.lotr.model.player.Player;
import vermesa.lotr.model.player.SauronPlayer;
import vermesa.lotr.serialization.utils.TestConfigLoader;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    SauronPlayer sauronPlayer;
    FellowshipPlayer fellowshipPlayer;
    Region region;

    static Stream<AddUnitsTestConfig> jsonConfigsProvider() throws Exception {
        // loadAllConfigs("/add_units_test_config.json") reads from src/test/resources
        List<AddUnitsTestConfig> configs = TestConfigLoader.loadAllConfigs("/test-data/region-test.json");

        // Wrap each AddUnitsTestConfig into an Arguments; JUnit will pass it to the test method.
        return configs.stream();
    }

    static Stream<AddUnitsTestConfig> regionTestConfigStream() {
        SauronPlayer sauronPlayer = new SauronPlayer(15, 10, 7);
        FellowshipPlayer fellowshipPlayer = new FellowshipPlayer(15, 10, 7);
        Region region = new Region(RegionType.Arnor, null);

        AddUnitsTestConfig regionTestConfig = new AddUnitsTestConfig();
        regionTestConfig.sauronPlayer = sauronPlayer;
        regionTestConfig.fellowshipPlayer = fellowshipPlayer;
        regionTestConfig.region = region;
        regionTestConfig.initialPlayerUnitsInRegion = sauronPlayer;
        regionTestConfig.initialUnitCountInRegion = 1;
        regionTestConfig.playerUnitsInRegion = sauronPlayer;
        regionTestConfig.playerUnitCountInRegion = 1;
        regionTestConfig.expectedPlayerUnitsInRegion = sauronPlayer;
        regionTestConfig.expectedUnitCountInRegion = 3;

        return Stream.of(regionTestConfig);
    }

    @BeforeEach
    void setUp() {
        sauronPlayer = new SauronPlayer(15, 10, 7);
        fellowshipPlayer = new FellowshipPlayer(15, 10, 7);
        region = new Region(RegionType.Arnor, null);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("jsonConfigsProvider")
    void addUnits(AddUnitsTestConfig config) {
        Region region = config.region;

        // Arrange
        if (config.initialUnitCountInRegion > 0) {
            region.addUnits(config.initialPlayerUnitsInRegion, config.initialUnitCountInRegion);
        }

        // Act
        region.addUnits(config.playerUnitsInRegion, config.playerUnitCountInRegion);

        // Assert
        assertEquals(config.expectedUnitCountInRegion, region.getUnitCount());
        assertEquals(config.expectedPlayerUnitsInRegion, region.getUnit());
    }

    @Test
    void removeUnits() {
    }

    @Test
    void removeFortress_Empty() {
    }

    @Test
    void removeFortress_Sauron() {

    }

    @Test
    void removeFortress_Fellowship() {

    }

    @Test
    void placeFortress_EmptyFellowship() {

    }

    @Test
    void placeFortress_EmptySauron() {

    }

    @Test
    void placeFortress_OccupiedFellowship() {
    }

    @Test
    void placeFortress_OccupiedSauron() {

    }

    public static class AddUnitsTestConfig {
        public String testCaseName;
        public SauronPlayer sauronPlayer;
        public FellowshipPlayer fellowshipPlayer;
        public Region region;

        // Initial config
        public Player initialPlayerUnitsInRegion;
        public int initialUnitCountInRegion;

        // Actual placing
        public Player playerUnitsInRegion;
        public int playerUnitCountInRegion;

        // Expected result
        public Player expectedPlayerUnitsInRegion;
        public int expectedUnitCountInRegion;

        @Override
        public String toString() {
            return testCaseName;
        }
    }
}