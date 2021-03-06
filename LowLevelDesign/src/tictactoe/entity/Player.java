package tictactoe.entity;

import java.util.ArrayList;

public class Player {
    private PieceEnum symbol;
    private String name;
    private PlayerType playerType;
    private ArrayList<Location> moves;

    public Player(int playerNo, String name, PlayerType playerType) {
        this.symbol = (playerNo == 1) ? PieceEnum.CIRCLE : PieceEnum.CROSS;
        this.name = name;
        this.playerType = playerType;
        this.moves = new ArrayList<Location>();
    }

    public PieceEnum getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void addMove(Location p) {
        this.moves.add(p);
    }

    public ArrayList<Location> getMoves() {
        return this.moves;
    }

    public String toString() {
        return this.name;
    }
}