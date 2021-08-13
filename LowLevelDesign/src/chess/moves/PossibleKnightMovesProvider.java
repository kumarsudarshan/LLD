package chess.moves;

import chess.conditions.MoveBaseCondition;
import chess.conditions.PieceCellOccupyBlocker;
import chess.conditions.PieceMoveFurtherCondition;
import chess.model.Board;
import chess.model.Cell;
import chess.model.Piece;
import chess.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PossibleKnightMovesProvider extends PossibleMovesProvider {

    public PossibleKnightMovesProvider(int maxSteps, MoveBaseCondition baseCondition,
                                       PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftUpCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getLeftDownCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightUpCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightDownCell, board, additionalBlockers, player));
        return null;
    }
}
