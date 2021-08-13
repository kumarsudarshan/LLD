package chess.moves;

import chess.conditions.MoveBaseCondition;
import chess.conditions.PieceCellOccupyBlocker;
import chess.conditions.PieceMoveFurtherCondition;
import chess.model.Board;
import chess.model.Cell;
import chess.model.Piece;
import chess.model.Player;

import java.util.List;

public class PossibleMovesProviderDiagonal extends PossibleMovesProvider {


    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}
