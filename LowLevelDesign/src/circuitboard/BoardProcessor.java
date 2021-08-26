package circuitboard;

import java.util.List;

interface BoardProcessor {
    int compute(List<Node> parents);

    static BoardProcessor getInstance(BoardType type) {
        switch (type) {

            case XOR:
                return new XorProcessor();
            case OR:
                return new OrProcessor();
            case AND:
                return new AndProcessor();
            case ON:
                return new OnProcessor();
            case OFF:
                return new OffProcessor();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
