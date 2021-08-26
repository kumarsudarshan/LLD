package circuitboard;

import java.util.List;

class Node {
    int id;
    List<Node> inputs;
    BoardType type;
    BoardProcessor processor;

    public Node(BoardType type) {
        this.type = type;
    }

    public void setInputs(List<Node> inputs) {
        this.inputs = inputs;
    }

    public Node(int id, BoardType type) {
        this.id = id;
        this.type = type;
        this.processor = BoardProcessor.getInstance(type);
    }

}
