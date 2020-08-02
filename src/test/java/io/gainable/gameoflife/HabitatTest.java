package io.gainable.gameoflife;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class HabitatTest {

    @Test
    void isRowOutOfBoundsPredicate__TrueWhenRowOutOfBounds() {
        HabitatConfiguration habitatConfiguration = new HabitatConfiguration(
                0, 0, Collections.emptyList());
        Habitat zeroHabitat = new Habitat(habitatConfiguration);
        HabitatCoordinate aCoordinate = new HabitatCoordinate(-1, 0);
        assertTrue(zeroHabitat.isCoordinateRowOutOfBounds(aCoordinate));
    }

    @Test
    void isColumnOutOfBoundsPredicate__TrueWhenColumnOutOfBounds() {
        HabitatConfiguration habitatConfiguration = new HabitatConfiguration(
                8, 12, Collections.emptyList());
        Habitat aHabitat = new Habitat(habitatConfiguration);
        HabitatCoordinate aCoordinate = new HabitatCoordinate(5, 12);
        assertTrue(aHabitat.isCoordinateColumnOutOfBounds(aCoordinate));
    }

    @Test
    void HabitateCoordinatePredicates__FalseWhenCoordinateInBounds() {
        HabitatConfiguration habitatConfiguration = new HabitatConfiguration(
                8, 12, Collections.emptyList());
        Habitat aHabitat = new Habitat(habitatConfiguration);
        HabitatCoordinate aCoordinate = new HabitatCoordinate(5, 5);
        assertFalse(aHabitat.isCoordinateColumnOutOfBounds(aCoordinate));
    }

    @Test
    void removeCoordinatesOutsideBoundaries() {
        HabitatCoordinate origin = new HabitatCoordinate(0, 0);
        Collection<HabitatCoordinate> boundingCoordinates =
                origin.generateBoundingCoordinates();

        HabitatConfiguration habitatConfiguration = new HabitatConfiguration(
                0, 0, Collections.emptyList());
        Habitat zeroHabitat = new Habitat(habitatConfiguration);
        Collection<HabitatCoordinate> emptyCoordinateCollection =
                zeroHabitat.removeCoordinatesOutsideBoundaries(boundingCoordinates);

        assertEquals(Collections.emptyList(), emptyCoordinateCollection);
    }

}