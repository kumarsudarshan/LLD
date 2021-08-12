package tictactoe;

public enum PieceEnum {

    CIRCLE ("O"),
    CROSS ("X"),
    EMPTY ("-");

    private String symbol;

    private PieceEnum(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return this.symbol;
    }

}