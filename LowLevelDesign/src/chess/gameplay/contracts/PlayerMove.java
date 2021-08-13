package chess.gameplay.contracts;

import chess.model.Cell;
import chess.model.Piece;
import lombok.Getter;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}
