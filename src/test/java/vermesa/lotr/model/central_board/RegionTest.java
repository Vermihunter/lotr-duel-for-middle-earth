package vermesa.lotr.model.central_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.EnumSource;

import vermesa.lotr.model.player.PlayerType;
import vermesa.lotr.serialization.utils.JsonConfigs;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    Region region;

    @BeforeEach
    void setUp() {
        region = new Region(null, null);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @JsonConfigs(value = AddUnitsTestConfig.class, resource = "/test-data/region-tests/add-units-region-test.json")
    void addUnits(AddUnitsTestConfig config) {
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

    @ParameterizedTest(name = "[{index}] {0}")
    @JsonConfigs(value = AddUnitsTestConfig.class, resource = "/test-data/region-tests/remove-units-region-test.json")
    void removeUnits_EnoughUnits(AddUnitsTestConfig config) {
        // Arrange
        if (config.initialUnitCountInRegion > 0) {
            region.addUnits(config.initialPlayerUnitsInRegion, config.initialUnitCountInRegion);
        }

        // Act
        region.removeUnits(config.playerUnitCountInRegion);

        // Assert
        assertEquals(config.expectedUnitCountInRegion, region.getUnitCount());
        assertEquals(config.expectedPlayerUnitsInRegion, region.getUnit());
    }

    @Test
    void removeUnits_TooFewUnits() {
        assertThrows(IllegalArgumentException.class, () -> region.removeUnits(1));
    }

    @Test
    void removeFortress_Empty() {
        assertThrows(IllegalArgumentException.class, region::removeFortress);
    }


    @ParameterizedTest
    @EnumSource(PlayerType.class)
    void removeFortress_Fellowship(PlayerType player) {
        // Arrange
        region.placeFortress(player);

        // Act
        region.removeFortress();

        // Assert
        assertNull(region.getFortress());
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @JsonConfigs(value = FortressTestConfig.class, resource = "/test-data/region-tests/place-fortress-test.json")
    void placeFortress_Correct(FortressTestConfig config) {
        // Arrange
        if (config.initialPlayer != null) {
            region.placeFortress(config.initialPlayer);
        }

        // Act
        region.placeFortress(config.playerPlacingFortress);

        // Assert
        assertEquals(config.playerHoldingFortress, region.getFortress());
    }


    @ParameterizedTest
    @EnumSource(PlayerType.class)
    void placeFortress_OccupiedFellowship(PlayerType player) {
        region.placeFortress(player);

        assertThrows(IllegalStateException.class, () -> region.placeFortress(player));
    }

    public static class FortressTestConfig {
        public String testCaseName;
        public PlayerType initialPlayer;
        public PlayerType playerPlacingFortress;
        public PlayerType playerHoldingFortress;

        @Override
        public String toString() {
            return testCaseName;
        }
    }

    public static class AddUnitsTestConfig {
        public String testCaseName;

        // Initial config
        public PlayerType initialPlayerUnitsInRegion;
        public int initialUnitCountInRegion;

        // Actual placing
        public PlayerType playerUnitsInRegion;
        public int playerUnitCountInRegion;

        // Expected result
        public PlayerType expectedPlayerUnitsInRegion;
        public int expectedUnitCountInRegion;

        @Override
        public String toString() {
            return testCaseName;
        }
    }
}