package snakeandladder.model;

import snakeandladder.exceptions.InvalidLadderPosition;

public class Ladder {
    // Each ladder will have its start position at some number and end position at a larger number.
    private int start;
    private int end;

    public Ladder(int start, int end) throws InvalidLadderPosition {
        if (start >= end) {
            throw new InvalidLadderPosition("start value of the ladder must be smaller than end value of ladder.");
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
