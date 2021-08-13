package chess.conditions;

import chess.model.Board;
import chess.model.Cell;
import chess.model.Piece;

/**
 * Condition interface to tell whether a piece can further move from a cell.
 */
public interface PieceMoveFurtherCondition {

    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}
