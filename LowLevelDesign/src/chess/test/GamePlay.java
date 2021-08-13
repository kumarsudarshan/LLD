package chess.test;

import chess.gameplay.contracts.PlayerMove;
import chess.model.*;
import chess.moves.VerticalMoveDirection;
import chess.test.helpers.TestHelpers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;

class TestPlayer extends Player {

    public TestPlayer(List<Piece> pieces) {
        super(pieces);
    }

    @Override
    public PlayerMove makeMove() {
        return null;
    }
}

public class GamePlay {

    @Test
    public void testSampleGameplay() {
        Board board = TestHelpers.createBoard();
        List<Piece> whitePieces = TestHelpers.piecesSet(Color.WHITE, board, 0, 1, VerticalMoveDirection.UP);
        List<Piece> blackPieces = TestHelpers.piecesSet(Color.BLACK, board, 7, 6, VerticalMoveDirection.DOWN);

        Player whitePlayer = new TestPlayer(whitePieces);
        Player blackPlayer = new TestPlayer(blackPieces);

        printBoard(board, "Initial");

        // Validate that queen has no possible moves initially.
        Piece whiteQ = board.getCellAtLocation(0, 4).getCurrentPiece();
        Assert.assertEquals(whiteQ.getColor(), Color.WHITE);
        Assert.assertEquals(whiteQ.getPieceType(), PieceType.QUEEN);

        List<Cell> whiteBishop1NextPossibleCells = whiteQ.nextPossibleCells(board, defaultAdditionalBlockers(), whitePlayer);
        Assert.assertTrue(whiteBishop1NextPossibleCells.isEmpty());


        // Move a pawn from front of queen.
        Piece whitePawn4 = board.getCellAtLocation(1, 4).getCurrentPiece();
        Assert.assertEquals(whitePawn4.getColor(), Color.WHITE);
        Assert.assertEquals(whitePawn4.getPieceType(), PieceType.PAWN);

        List<Cell> whitePawn4NextPossibleCells = whitePawn4.nextPossibleCells(board, defaultAdditionalBlockers(), whitePlayer);
        Assert.assertEquals(2, whitePawn4NextPossibleCells.size());
        whitePawn4.move(whitePlayer, whitePawn4NextPossibleCells.get(1), board, defaultAdditionalBlockers());
        printBoard(board, "Post PAWN Move");
        Assert.assertEquals(whitePawn4.getCurrentCell(), whitePawn4NextPossibleCells.get(1));


        // Now queen should be able to make 2 moves since its pawn has moved 2 moves.
        List<Cell> whiteQMove2 = whiteQ.nextPossibleCells(board, defaultAdditionalBlockers(), whitePlayer);
        Assert.assertEquals(whiteQMove2.size(), 2);
    }

    void printBoard(Board board, String title) {
        System.out.println("\n\n" + title);
        for (int i = board.getBoardSize() - 1; i >= 0; i--) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                System.out.print(displayChar(board.getCellAtLocation(i, j)) + " ");
            }
            System.out.println();
        }
    }

    String displayChar(Cell cell) {
        if (cell.getCurrentPiece() == null) {
            return "-";
        }
        switch (cell.getCurrentPiece().getPieceType()) {
            case PAWN:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "p" : "P";
            case BISHOP:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "b" : "B";
            case KING:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "k" : "K";
            case ROOK:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "r" : "R";
            case QUEEN:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "q" : "Q";
            case KNIGHT:
                return cell.getCurrentPiece().getColor().equals(Color.BLACK) ? "h" : "H";
        }
        return "0";
    }
}
