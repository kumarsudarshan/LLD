package circuitboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardSystem {
    Map<Integer, Node> nodes;
    Map<Integer, List<Node>> parents;

    public BoardSystem() {
        nodes = new HashMap<>();
        parents = new HashMap<>();

    }

    void initialize(int[] nodes, int[][] edges, String[] types) {

        this.nodes.clear();
        this.parents.clear();

        assert nodes.length != 0;
        assert edges.length != 0;
        assert nodes.length == types.length;

        for (int i = 0; i < nodes.length; i++) {
            addNode(nodes[i], Enum.valueOf(BoardType.class, types[i]));
        }
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }

    }

    void addNode(int id, BoardType type) {
        Node node = new Node(id, type);
        // validate node with id not exists
        nodes.put(id, node);
    }

    void addEdge(int parentId, int childId) {
        // validate not cycle
        parents.putIfAbsent(childId, new ArrayList<>());
        parents.get(childId).add(nodes.get(parentId));
    }

    List<Integer> sortedNodes;
    Map<Integer, Integer> resultMap;

    int compute() {
        sortedNodes = new ArrayList<>();
        resultMap = new HashMap<>();

        for (int nodeId : nodes.keySet()) {
            dfs(nodeId);
        }

        int last = sortedNodes.get(sortedNodes.size() - 1);
        return resultMap.get(last);
    }


    private void dfs(int nodeId) {
        if (resultMap.containsKey(nodeId)) {
            return;
        }
        Node node = nodes.get(nodeId);
        if (node.inputs == null && this.parents.containsKey(nodeId)) {
            node.setInputs(this.parents.get(nodeId));
        }
        if (node.inputs == null) return;

        for (Node parent : node.inputs) {
            dfs(parent.id);
        }
        int res = node.processor.compute(node.inputs);
        resultMap.put(nodeId, res);
        sortedNodes.add(nodeId);
    }
}

