import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private V start;
    private UnweightedGraph<V> graph;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.graph = graph;
        this.start = start;
        this.edgeTo = new HashMap<>();
        bfs(start);
    }

    private void bfs(V start) {
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Vertex<V> vertex = graph.getVertex(current);
            if (vertex == null) continue;

            for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData);
                    edgeTo.put(neighborData, current);
                    queue.add(neighborData);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        List<V> path = new ArrayList<>();
        if (!edgeTo.containsKey(destination) && !destination.equals(start)) {
            return path;
        }
        for (V v = destination; !v.equals(start); v = edgeTo.get(v)) {
            path.add(0, v);
        }
        path.add(0, start);
        return path;
    }
}
