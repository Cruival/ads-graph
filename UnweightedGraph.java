import java.util.HashMap;
import java.util.Map;

public class UnweightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest) {
        Vertex<V> srcVertex = vertices.getOrDefault(source, new Vertex<>(source));
        Vertex<V> destVertex = vertices.getOrDefault(dest, new Vertex<>(dest));

        srcVertex.addAdjacentVertex(destVertex, 1.0);
        vertices.put(source, srcVertex);
        vertices.put(dest, destVertex);

        if (!directed) {
            destVertex.addAdjacentVertex(srcVertex, 1.0);
        }
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }
}
