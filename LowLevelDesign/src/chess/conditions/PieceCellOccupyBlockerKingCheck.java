package chess.conditions;

import chess.model.Board;
import chess.model.Cell;
import chess.model.Piece;
import chess.model.Player;

/**
 * This tells whether making piece move to a cell will attract check for king.
 */
public class PieceCellOccupyBlockerKingCheck implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(final Cell cell, final Piece piece, final Board board, Player player) {
        Cell pieceOriginalCell = piece.getCurrentCell();
        piece.setCurrentCell(cell);
        boolean playerGettingCheckByMove = board.isPlayerOnCheck(player);
        if(playerGettingCheckByMove){
            piece.setCurrentCell(pieceOriginalCell);
        }
        return playerGettingCheckByMove;
    }
}
