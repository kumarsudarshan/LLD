package chess.model;

import chess.gameplay.contracts.PlayerMove;

import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(List<Piece> pieces) {
        super(pieces);
    }

    @Override
    public PlayerMove makeMove() {
        return null;
    }
}
