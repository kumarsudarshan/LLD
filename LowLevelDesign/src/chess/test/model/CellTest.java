package chess.test.model;

import chess.model.Cell;
import chess.test.helpers.TestHelpers;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testFreeCell() {
        Cell cell = new Cell(0, 0);
        assertTrue(cell.isFree());
    }

    @Test
    void testOccupiedCell() {
        Cell cell = new Cell(0, 0);
        cell.setCurrentPiece(TestHelpers.randomPiece());
        assertFalse(cell.isFree());
    }
}