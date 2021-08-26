package circuitboard;

class BoardManager {
    public static void main(String[] args) {
        BoardSystem boardSystem = new BoardSystem();

        boardSystem.initialize(
                new int[]{1, 2, 3},
                new int[][]{{1, 3}, {2, 3}},
                new String[]{"OFF", "ON", "XOR"}
        );

        Preconditions.validateEquals(1, boardSystem.compute());

        boardSystem.initialize(
                new int[]{1, 2, 3, 4, 5},
                new int[][]{{1, 3}, {2, 3}, {1, 4}, {2, 4}, {3, 5}, {4, 5}},
                new String[]{"ON", "OFF", "XOR", "AND", "OR"}
        );
        Preconditions.validateEquals(1, boardSystem.compute());

    }

}