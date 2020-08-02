package io.gainable.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    @Test
    void deadNeighboursCountTest() {
        Collection<Cell> someDeadNeighbours = Arrays.asList(new Cell(false), new Cell(false));
        Environment environment = new Environment(someDeadNeighbours);
        assertEquals(0, environment.liveNeighbourCount());
    }

    @Test
    void anAliveNeighboursCountTest() {
        Collection<Cell> anAliveNeighbour = Arrays.asList(
                new Cell(false),
                new Cell(false),
                new Cell(true),
                new Cell(false)
        );
        Environment environment = new Environment(anAliveNeighbour);
        assertEquals(1, environment.liveNeighbourCount());
    }

    @Test
    void someAliveNeighboursCountTest() {
       Collection<Cell> someAliveNeighbours = Arrays.asList(
               new Cell(false),
               new Cell(false),
               new Cell(true),  // 1
               new Cell(false),
               new Cell(false),
               new Cell(false),
               new Cell(true), // 2
               new Cell(true)  // 3
       );
        Environment environment = new Environment( someAliveNeighbours);
        assertEquals(3, environment.liveNeighbourCount());
    }

    @Test
    void allAliveNeighboursCountTest() {
        Collection<Cell> allAliveNeighbours = Arrays.asList(
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true)
        );
        Environment environment = new Environment( allAliveNeighbours);
        assertEquals(8, environment.liveNeighbourCount());
    }
}