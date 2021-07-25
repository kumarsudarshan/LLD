package snakeandladder;

import snakeandladder.exceptions.InvalidLadderPosition;
import snakeandladder.exceptions.InvalidSnakePosition;
import snakeandladder.model.*;
import snakeandladder.service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws InvalidSnakePosition, InvalidLadderPosition {
        int noOfSnakes = 5;
        List<Snake> snakes = new ArrayList<Snake>();
        snakes.add(new Snake(72, 18));
        snakes.add(new Snake(72, 18));
        snakes.add(new Snake(72, 18));
        snakes.add(new Snake(72, 18));
        snakes.add(new Snake(72, 18));

        int noOfLadders = 6;
        List<Ladder> ladders = new ArrayList<Ladder>();
        ladders.add(new Ladder(10, 23));
        ladders.add(new Ladder(10, 23));
        ladders.add(new Ladder(10, 23));
        ladders.add(new Ladder(10, 23));
        ladders.add(new Ladder(10, 23));
        ladders.add(new Ladder(10, 23));

        int noOfPlayers = 4;
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("kumar"));
        players.add(new Player("mahesh"));
        players.add(new Player("bhushan"));
        players.add(new Player("punita"));

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setShouldAllowMultipleDiceRollOnSix(true);
        snakeAndLadderService.setShouldGameContinueTillLastPlayer(true);
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);

        snakeAndLadderService.startGame();
    }
}