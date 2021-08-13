package chess.model;

import chess.gameplay.contracts.PlayerMove;

import java.util.List;

public class ComputerPlayer extends Player {
    public ComputerPlayer(List<Piece> pieces) {
        super(pieces);
    }

    @Override
    public PlayerMove makeMove() {
        return null;
    }
}
