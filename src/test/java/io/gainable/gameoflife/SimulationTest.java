package io.gainable.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void zeroHabitat__isIdempotent() {
        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                0, 0, Collections.emptyList());
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertEquals(nextGenerationConfiguration.getSeedColony(), Collections.emptyList());
    }

    @Test
    void emptyHabitat__isIdempotent() {
        HabitatConfiguration anEmptyHabitatConfiguration = new HabitatConfiguration(
                3, 3, Collections.emptyList());
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(anEmptyHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertEquals(nextGenerationConfiguration.getWidth(), anEmptyHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), anEmptyHabitatConfiguration.getLength());
        assertEquals(nextGenerationConfiguration.getSeedColony(), Collections.emptyList());
    }

    @Test
    void aSingleCell__Dies() {
        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(0, 0)
        );
        HabitatConfiguration anHabitatConfiguration = new HabitatConfiguration(
                1, 1, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(anHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertEquals(nextGenerationConfiguration.getWidth(), anHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), anHabitatConfiguration.getLength());
        assertEquals(nextGenerationConfiguration.getSeedColony(), Collections.emptyList());
    }

    @Test
    void aSingleCellInAnEmptyEnvironment__Dies() {
        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(1, 1)
        );
        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                3, 3, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertEquals(nextGenerationConfiguration.getSeedColony(), Collections.emptyList());
    }

    // See the first example in the problem description
    @Test
    void aDeadCellWithThreeLiveNeighboursInTheNextGeneration__BecomesALiveCell() {

        Collection<HabitatCoordinate> expectedSeedColony =
                Arrays.asList(
                        new HabitatCoordinate(1, 1),
                        new HabitatCoordinate(2, 1)
                );

        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(2, 0),
                new HabitatCoordinate(2, 1),
                new HabitatCoordinate(2, 2)
        );

        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                3, 3, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertTrue(nextGenerationConfiguration.getSeedColony().contains(new HabitatCoordinate(1, 1)));

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertThat(nextGenerationConfiguration.getSeedColony(), containsInAnyOrder(expectedSeedColony.toArray()));
    }

    // See the second example in the problem description
    @Test
    void aLivingCellWithLessThanTwoLiveNeighboursInTheNextGeneration__Dies() {

        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(1, 1),
                new HabitatCoordinate(2, 2)
        );

        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                3, 3, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertFalse(nextGenerationConfiguration.getSeedColony().contains(new HabitatCoordinate(1, 1)));

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertEquals(nextGenerationConfiguration.getSeedColony(), Collections.emptyList());
    }

    // See the third example in the problem description
    @Test
    void aLivingCellWithTwoOrThreeLiveNeighboursInTheNextGeneration__Lives() {

        Collection<HabitatCoordinate> expectedSeedColony =
                Arrays.asList(
                        new HabitatCoordinate(1, 0),
                        new HabitatCoordinate(1, 1),
                        new HabitatCoordinate(1, 2),
                        new HabitatCoordinate(2, 0),
                        new HabitatCoordinate(2, 1),
                        new HabitatCoordinate(2, 2)
                );

        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(1, 0),
                new HabitatCoordinate(1, 1),
                new HabitatCoordinate(2, 1),
                new HabitatCoordinate(2, 2)
        );

        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                3, 3, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertTrue(nextGenerationConfiguration.getSeedColony().contains(new HabitatCoordinate(1, 1)));

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertThat(nextGenerationConfiguration.getSeedColony(), containsInAnyOrder(expectedSeedColony.toArray()));
    }

    // See the fouth example in the problem description
    @Test
    void aLivingCellWithMoreThanThreeLiveNeighboursInTheNextGeneration__Dies() {

        Collection<HabitatCoordinate> expectedSeedColony =
                Arrays.asList(
                        new HabitatCoordinate(0, 0),
                        new HabitatCoordinate(0, 2),
                        new HabitatCoordinate(2, 0),
                        new HabitatCoordinate(2, 2)
                );

        Collection<HabitatCoordinate> aSeedColony = Arrays.asList(
                new HabitatCoordinate(0, 0),
                new HabitatCoordinate(0, 1),
                new HabitatCoordinate(0, 2),
                new HabitatCoordinate(1, 0),
                new HabitatCoordinate(1, 1),
                new HabitatCoordinate(1, 2),
                new HabitatCoordinate(2, 0),
                new HabitatCoordinate(2, 1),
                new HabitatCoordinate(2, 2)
        );

        HabitatConfiguration aHabitatConfiguration = new HabitatConfiguration(
                3, 3, aSeedColony);
        HabitatConfiguration nextGenerationConfiguration = Simulation.evolute(aHabitatConfiguration);
        Collection<HabitatCoordinate> nextGenerationSeedColony = nextGenerationConfiguration.getSeedColony();

        assertFalse(nextGenerationConfiguration.getSeedColony().contains(new HabitatCoordinate(1, 1)));

        assertEquals(nextGenerationConfiguration.getWidth(), aHabitatConfiguration.getWidth());
        assertEquals(nextGenerationConfiguration.getLength(), aHabitatConfiguration.getLength());
        assertThat(nextGenerationConfiguration.getSeedColony(), containsInAnyOrder(expectedSeedColony.toArray()));
    }

}