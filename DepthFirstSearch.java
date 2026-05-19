import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private Set<V> visited;
    private V start;
    private UnweightedGraph<V> graph;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.graph = graph;
        this.start = start;
        this.edgeTo = new HashMap<>();
        this.visited = new HashSet<>();
        dfs(start);
    }

    private void dfs(V current) {
        visited.add(current);
        Vertex<V> vertex = graph.getVertex(current);
        if (vertex == null) return;

        for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
            V neighborData = neighbor.getData();
            if (!visited.contains(neighborData)) {
                edgeTo.put(neighborData, current);
                dfs(neighborData);
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
