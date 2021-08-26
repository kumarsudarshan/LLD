package circuitboard;

import java.util.List;

class AndProcessor implements BoardProcessor {

    @Override
    public int compute(List<Node> inputs) {
        int res = 1;
        for (Node parent : inputs) {
            res &= parent.processor.compute(parent.inputs);
        }
        return res;
    }
}
