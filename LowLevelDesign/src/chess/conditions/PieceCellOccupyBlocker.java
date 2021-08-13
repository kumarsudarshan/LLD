package chess.conditions;


import chess.model.Board;
import chess.model.Cell;
import chess.model.Piece;
import chess.model.Player;

/**
 * This check tells whether a piece can occupy a given cell in the board or not.
 */
public interface PieceCellOccupyBlocker {

    boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player);
}
