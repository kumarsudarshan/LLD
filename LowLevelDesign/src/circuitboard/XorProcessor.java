package circuitboard;

import java.util.List;

class XorProcessor implements BoardProcessor {

    @Override
    public int compute(List<Node> inputs) {
        int res = 0;
        for (Node node : inputs) {
            res ^= node.processor.compute(node.inputs);
        }
        return res;
    }
}
