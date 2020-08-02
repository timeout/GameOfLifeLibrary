package io.gainable.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void aDeadCellWithExactlyThreeNeighboursAlive__IsBoughtBackToLife() {
        Cell aDeadCell = new Cell(false);
        Collection<Cell> threeLivingCells = Arrays.asList(
                new Cell(true),
                new Cell(true),
                new Cell(true)
        );
        Environment anEnvironment = new Environment(threeLivingCells);
        Cell testCell = aDeadCell.transition(anEnvironment);
        assertTrue(testCell.isAlive());
    }

    @Test
    void aLivingCellWithLessThanTwoLivingCells__DiesOfLoneliness() {
        Cell aLivingCell = new Cell(true);
        Collection<Cell> oneLivingCell = Arrays.asList(
                new Cell(true)
        );
        Environment anEnvironment = new Environment(oneLivingCell);
        Cell testCell = aLivingCell.transition(anEnvironment);
        assertFalse(testCell.isAlive());

        Collection<Cell> noLivingCells = Collections.emptyList();
        anEnvironment = new Environment(noLivingCells);
        testCell = aLivingCell.transition(anEnvironment);
        assertFalse(testCell.isAlive());
    }

    @Test
    void aLivingCellWithTwoOrThreeLivingCells__RemainsAlive() {
        Cell aLivingCell = new Cell(true);
        Collection<Cell> twoLivingCells = Arrays.asList(
                new Cell(true),
                new Cell(true)
        );
        Environment anEnvironment = new Environment(twoLivingCells);
        Cell testCell = aLivingCell.transition(anEnvironment);
        assertTrue(testCell.isAlive());

        Collection<Cell> threeLivingCells = Stream.concat(
                twoLivingCells.stream(),
                Stream.of(new Cell(true))
        ).collect(Collectors.toList());
        anEnvironment = new Environment(threeLivingCells);
        testCell = aLivingCell.transition(anEnvironment);
        assertTrue(testCell.isAlive());
    }

    @Test
    void aLivingCellWithMoreThanThreeLivingNeighbours__DiesOfHunger() {
        Cell aLivingCell = new Cell(true);
        Collection<Cell> fourLivingCells = Arrays.asList(
                new Cell(true),
                new Cell(true),
                new Cell(true),
                new Cell(true)
        );
        Environment anEnvironment = new Environment(fourLivingCells);
        Cell testCell = aLivingCell.transition(anEnvironment);
        assertFalse(testCell.isAlive());
    }
}