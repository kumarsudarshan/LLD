package snakeandladder.model;

import snakeandladder.exceptions.InvalidSnakePosition;

public class Snake {
    // Each snake will have its head at some number and its tail at a smaller number.
    private int start;
    private int end;

    public Snake(int start, int end) throws InvalidSnakePosition {
        if (start <= end) {
            throw new InvalidSnakePosition("Head value of the snake must be greater than tail value.");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}