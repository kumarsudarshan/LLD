package circuitboard;

import java.util.List;

class OrProcessor implements BoardProcessor {

    @Override
    public int compute(List<Node> inputs) {
        int res = 0;
        for (Node parent : inputs) {
            res |= parent.processor.compute(parent.inputs);
        }
        return res;
    }
}
