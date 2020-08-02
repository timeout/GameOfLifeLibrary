package io.gainable.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class HabitatCoordinateTest {

    @Test
    void calculateNeighbourhoodCoordinates() {
        Collection<HabitatCoordinate> expectedBoundingCoordinates =
                Arrays.asList(
                        new HabitatCoordinate(-1, -1),
                        new HabitatCoordinate(-1, 0),
                        new HabitatCoordinate(-1, 1),
                        new HabitatCoordinate(0, -1),
                        new HabitatCoordinate(0, 1),
                        new HabitatCoordinate(1, -1),
                        new HabitatCoordinate(1, 0),
                        new HabitatCoordinate(1, 1)

                );
        HabitatCoordinate origin = new HabitatCoordinate(0, 0);
        Collection<HabitatCoordinate> someBoundingCoordinates =
                origin.generateBoundingCoordinates();
        assertEquals(someBoundingCoordinates.size(), expectedBoundingCoordinates.size());
        someBoundingCoordinates.forEach(
                coordinate -> assertTrue(expectedBoundingCoordinates.contains(coordinate)));
    }
}