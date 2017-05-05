import java.util.*;

/**
 * A Class for a graph that represents the coffee shops and warehouses
 */
public class Graph {
    
    private ArrayList<Vertex> vertices;         // list of verices in the graph
    
    /**
     * Creates a new graph with no vertices
     */
    public Graph() {
        vertices = new ArrayList<Vertex>();
    }
    
    /**
     * Adds a new vertex to the graph
     * @param aVertex new vertex
     */
    public void addVertex(Vertex aVertex) {
        vertices.add(aVertex);
    }
    
    /**
     * Adds all edges between every vertex to each shop
     */
    public void addEdges() {
        for (int i=0; i<vertices.size(); i++) {
                
            for (int j=0; j<vertices.size(); j++) {
                if (vertices.get(j) instanceof Shop && i != j) {
                    vertices.get(i).addEdge(new Edge(vertices.get(i), vertices.get(j)));
                }
            }
        }
    }
    
    /**
     * Prints the info of the graph to the terminal
     */
    public void printGraph() {
        for (Vertex v : vertices) {
            if (v instanceof Shop) {
                System.out.print("Shop: " + v.getId() + ", ");
                v.printEdges();
                System.out.println(" ");
            }
            else {
                System.out.print("Warehouse: " + v.getId() + ", ");
                v.printEdges();
                System.out.println(" ");
            }
            
        }
    }
    
    /**
     * Returns an array list of all vertices in the graph
     */
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
}
