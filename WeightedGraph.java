import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> srcVertex = vertices.getOrDefault(source, new Vertex<>(source));
        Vertex<V> destVertex = vertices.getOrDefault(dest, new Vertex<>(dest));

        srcVertex.addAdjacentVertex(destVertex, weight);
        vertices.put(source, srcVertex);
        vertices.put(dest, destVertex);

        if (!directed) {
            destVertex.addAdjacentVertex(srcVertex, weight);
        }
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }
}
