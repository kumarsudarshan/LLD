package tictactoe;

import tictactoe.entity.Location;
import tictactoe.entity.Player;
import tictactoe.entity.PlayerType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game implements TicTacToe {
    private Scanner sc;
    private int turnCount;
    private Player player1;
    private Player player2;
    private Board board;
    private int dimension;

    public Game() {
        this.turnCount = 0;
        this.dimension = 3;
        this.board = new Board(dimension);
    }

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

        System.out.println(player1 + " " + player1.getSymbol());
        for (int i = 0; i < player1Moves.size(); i++) {
            System.out.println("Move" + (2 * i + 1) + " : " + player1Moves.get(i));
        }

        ArrayList<Location> player2Moves = this.player2.getMoves();

        System.out.println(player2 + " " + player2.getSymbol());
        for (int i = 0; i < player2Moves.size(); i++) {
            System.out.println("Move" + (2 * i + 2) + " : " + player2Moves.get(i));
        }
    }

    @Override
    public void configure() {
        sc = new Scanner(System.in);

        System.out.println("Board size: ");
        Integer boardSize = sc.nextInt();
        this.dimension = boardSize;
        this.board = new Board(this.dimension);

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
        if (playerType1.equals(PlayerType.COMPUTER)) {
            playerName1 = PlayerType.COMPUTER.name() + "-1";
        } else {
            playerName1 = sc.next();
        }
        System.out.println(playerName1);
        this.player1 = new Player(1, playerName1, playerType1);

        System.out.println("Enter player 2's name:");
        String playerName2 = "";
        if (playerType2.equals(PlayerType.COMPUTER)) {
            playerName2 = PlayerType.COMPUTER.name() + "-2";
        } else {
            playerName2 = sc.next();
        }
        System.out.println(playerName2);
        this.player2 = new Player(2, playerName2, playerType2);
    }

    @Override
    public void playGame() {
        while (true) {
            // Showing current board
            System.out.println("Current Board");
            this.board.getBoard();

            // Getting current player
            Player player = this.getPlayer();

            // Asking player for move
            try {
                System.out.println(player.getName() + "'s move");
                System.out.println("Enter row:");
                int x = player.getPlayerType().equals(PlayerType.COMPUTER) ? new Random().nextInt(this.dimension) : sc.nextInt();
                System.out.println("Enter col:");
                int y = player.getPlayerType().equals(PlayerType.COMPUTER) ? new Random().nextInt(this.dimension) : sc.nextInt();
                Location point = new Location(x, y);


                // Making the move
                this.board.makeMove(point, player.getSymbol());
                player.addMove(point);
                this.turnCount++;
                if (this.board.checkWinStatus(point)) {
                    System.out.println("Current Board");
                    this.board.getBoard();
                    this.showAllMoves();
                    System.out.println("Game won by " + player + " !!!!");
                    sc.close();
                    break;
                }
                if (this.turnCount == (dimension * dimension)) {
                    System.out.println("Game Draw!!!");
                    sc.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);

//                System.out.println("Want to quit? - Y/N");
//                String option = sc.next();
//                if(option.equalsIgnoreCase("Y") || option.equalsIgnoreCase("YES")){
//                    quit();
//                }
            }
        }
    }

    @Override
    public void quit() {
        sc.close();
        System.out.println("Game quit.");
        System.exit(0);
    }
}