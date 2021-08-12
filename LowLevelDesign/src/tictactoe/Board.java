package tictactoe;

public class Board {

    private PieceEnum[][] board;
    private int dimension;

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new PieceEnum[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = PieceEnum.EMPTY;
            }
        }
    }

    public void getBoard() {
        for (int i = -1; i < this.dimension; i++) {
            for (int j = -1; j < this.dimension; j++) {
                if (i == -1 && j == -1) {
                    System.out.print(" \t");
                } else if (i == -1) {
                    System.out.print(j + "\t");
                } else if (j == -1) {
                    System.out.print(i + "\t");
                } else {
                    System.out.print(this.board[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    protected void makeMove(Location point, PieceEnum piece) {
        if (point.getX() >= 0 && point.getX() < this.dimension && point.getY() >= 0 && point.getY() < this.dimension) {
            if (this.board[point.getX()][point.getY()] == PieceEnum.EMPTY) {
                // If move is allowed
                this.board[point.getX()][point.getY()] = piece;
                System.out.println("Move made by " + piece + " at " + point);
            } else {
                throw new IllegalArgumentException("Location selected is already occupied");
            }
        } else {
            throw new IllegalArgumentException("Location selected is out of boundary");
        }
    }

    protected boolean checkWinStatus(Location point) {
        PieceEnum candidate = this.board[point.getX()][point.getY()];

        int rowWin = 0;
        int colWin = 0;
        int diagWin = 0;
        int revDiagWin = 0;

        for (int i = 0; i < this.dimension; i++) {
            // Check row
            if (this.board[point.getX()][i] == candidate) {
                rowWin++;
            }
            // Check col
            if (this.board[i][point.getY()] == candidate) {
                colWin++;
            }
            // Check diagonal
            if (this.board[i][i] == candidate) {
                diagWin++;
            }
            // Check reverse Diagonal
            if (this.board[i][this.dimension - i - 1] == candidate) {
                revDiagWin++;
            }
        }

        if (rowWin == this.dimension || colWin == this.dimension || diagWin == this.dimension || revDiagWin == this.dimension) {
            return true;
        } else {
            return false;
        }
    }
}