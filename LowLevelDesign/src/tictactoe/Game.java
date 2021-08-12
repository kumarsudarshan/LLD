package tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int turnCount;
    private Player player1;
    private Player player2;
    private Board board;
    private int dimension;

    public Game(int dimension) {
        this.turnCount = 0;
        this.dimension = dimension;
        this.board = new Board(dimension);
    }

    private Player getPlayer() {
        return (this.turnCount % 2 == 0) ? this.player1 : this.player2;
    }

    private void showAllMoves() {
        ArrayList<Location> player1Moves = this.player1.getMoves();

        System.out.println(player1 + " " + player2.getSymbol());
        for (int i = 0; i < player1Moves.size(); i++) {
            System.out.println("Move" + (2 * i + 1) + " : " + player1Moves.get(i));
        }

        ArrayList<Location> player2Moves = this.player2.getMoves();

        System.out.println(player2 + " " + player2.getSymbol());
        for (int i = 0; i < player2Moves.size(); i++) {
            System.out.println("Move" + (2 * i + 2) + " : " + player2Moves.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Board size: ");
        Integer boardSize = sc.nextInt();
        Game game = new Game(boardSize);

        System.out.println("Select Mode: (Human Vs Human/Human Vs Computer/Computer Vs Computer) - 1/2/3");
        Integer mode = sc.nextInt();

        PlayerType playerType1;
        PlayerType playerType2;
        if (mode == 1) {
            playerType1 = PlayerType.HUMAN;
            playerType2 = PlayerType.HUMAN;
        } else if (mode == 2) {
            playerType1 = PlayerType.HUMAN;
            playerType2 = PlayerType.COMPUTER;
        } else if (mode == 3) {
            playerType1 = PlayerType.COMPUTER;
            playerType2 = PlayerType.COMPUTER;
        } else {
            throw new IllegalArgumentException("Mode value is not correct. Please select either of 1/2/3");
        }

        System.out.println("Enter player 1's name:");
        String playerName1 = "";
        if(playerType1.equals(PlayerType.COMPUTER)){
            playerName1 = PlayerType.COMPUTER.name() + "-1";
        } else {
            playerName1 = sc.next();
        }
        System.out.println(playerName1);
        game.player1 = new Player(1, playerName1, playerType1);

        System.out.println("Enter player 2's name:");
        String playerName2 = "";
        if(playerType2.equals(PlayerType.COMPUTER)){
            playerName2 = PlayerType.COMPUTER.name() + "-2";
        } else {
            playerName2 = sc.next();
        }
        System.out.println(playerName2);
        game.player2 = new Player(2, playerName2, playerType2);


        while (true) {
            // Showing current board
            System.out.println("Current Board");
            game.board.getBoard();

            // Getting current player
            Player player = game.getPlayer();

            // Asking player for move
            System.out.println(player.getName() + "'s move");
            System.out.println("Enter row:");
            int x = player.getPlayerType().equals(PlayerType.COMPUTER) ? new Random().nextInt(game.dimension) : sc.nextInt();
            System.out.println("Enter col:");
            int y = player.getPlayerType().equals(PlayerType.COMPUTER) ? new Random().nextInt(game.dimension) : sc.nextInt();
            Location point = new Location(x, y);

            try {
                // Making the move
                game.board.makeMove(point, player.getSymbol());
                player.addMove(point);
                game.turnCount++;
                if (game.board.checkWinStatus(point)) {
                    System.out.println("Current Board");
                    game.board.getBoard();
                    game.showAllMoves();
                    System.out.println("Game won by " + player + " !!!!");
                    sc.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

}