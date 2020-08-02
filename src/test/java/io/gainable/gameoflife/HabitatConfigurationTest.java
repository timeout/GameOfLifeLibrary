package io.gainable.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HabitatConfigurationTest {

    @Test
    void negativeWidths__throwBadConfigurationError() {
        assertThrows(BadConfigurationError.class, () -> {
            new HabitatConfiguration(-10, 5, Collections.emptyList());
        });
    }

    @Test
    void negativeLengths__throwBadConfigurationError() {
        assertThrows(BadConfigurationError.class, () -> {
            new HabitatConfiguration(1, -1, Collections.emptyList());
        });
    }

    @Test
    void seedColonyDefinitionOutsideWidth__throwsBadConfigurationError () {
        Collection<HabitatCoordinate> anOutOfBoundsSeedColony = Arrays.asList(
                new HabitatCoordinate(2, 3),
                new HabitatCoordinate(10, 7)
        );
        assertThrows(BadConfigurationError.class, () -> {
           new HabitatConfiguration(8, 8, anOutOfBoundsSeedColony);
        });
    }

    @Test
    void seedColonyDefinitionOutsideLength__throwsBadConfigurationError () {
        Collection<HabitatCoordinate> anOutOfBoundsSeedColony = Arrays.asList(
                new HabitatCoordinate(0, 7),
                new HabitatCoordinate(0, 8),
                new HabitatCoordinate(1, 7)
        );
        assertThrows(BadConfigurationError.class, () -> {
            new HabitatConfiguration(8, 8, anOutOfBoundsSeedColony);
        });
    }
}