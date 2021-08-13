package chess.test.model;

import chess.model.Cell;
import chess.test.helpers.TestHelpers;
import org.junit.Assert;
import org.junit.Test;


class CellTest {

    @Test
    void testFreeCell() {
        Cell cell = new Cell(0, 0);
        Assert.assertTrue(cell.isFree());
    }

    @Test
    void testOccupiedCell() {
        Cell cell = new Cell(0, 0);
        cell.setCurrentPiece(TestHelpers.randomPiece());
        Assert.assertFalse(cell.isFree());
    }
}