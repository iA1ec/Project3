import java.util.*;

/**
 * A Class for a graph that represents the coffee shops are warehouses
 */
public class Graph {
    
    private List<Vertex> vertices; 
    private int size; //The number of vertices
    
    public Graph() {
        vertices = new ArrayList<Vertex>();
    }
    
    public void addVertex(Vertex aVertex) {
        vertices.add(aVertex);
    }
}
