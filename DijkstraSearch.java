import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distTo;
    private Map<V, V> edgeTo;
    private V start;
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.graph = graph;
        this.start = start;
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        dijkstra(start);
    }

    private void dijkstra(V start) {
        for (V v : graph.getVertices().keySet()) {
            distTo.put(v, Double.MAX_VALUE);
        }
        distTo.put(start, 0.0);

        PriorityQueue<V> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.get(v)));
        pq.add(start);

        while (!pq.isEmpty()) {
            V current = pq.poll();
            Vertex<V> vertex = graph.getVertex(current);
            if (vertex == null) continue;

            for (Map.Entry<Vertex<V>, Double> entry : vertex.getAdjacentVertices().entrySet()) {
                V neighbor = entry.getKey().getData();
                double newDist = distTo.get(current) + entry.getValue();

                if (newDist < distTo.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(neighbor);
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
