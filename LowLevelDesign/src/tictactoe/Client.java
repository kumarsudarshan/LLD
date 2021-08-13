package tictactoe;

import tictactoe.entity.Player;
import tictactoe.entity.PlayerType;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Game game = new Game();
        game.configure();
//        Game game = Game.configure();
        game.playGame();
    }
}
