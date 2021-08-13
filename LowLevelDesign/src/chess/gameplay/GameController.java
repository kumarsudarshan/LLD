package chess.gameplay;


import chess.gameplay.contracts.PlayerMove;
import chess.model.Board;
import chess.model.Player;

import java.util.List;

import static chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;


public class GameController {

    public static void gameplay(List<Player> players, Board board) {
        int currentPlayer = 0;
        while (true) {
            Player player = players.get(currentPlayer);
            //TODO: Check if current player has any move possible. If no move possible, then its checkmate.
            PlayerMove playerMove = player.makeMove();
            playerMove.getPiece().move(player, playerMove.getToCell(), board, defaultAdditionalBlockers());
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}
