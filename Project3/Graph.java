import java.util.*;

/**
 * A Class for a graph that represents the coffee shops are warehouses
 */
public class Graph {
    
    private ArrayList<Vertex> vertices; 
    private int size; //The number of vertices
    
    public Graph() {
        vertices = new ArrayList<Vertex>();
    }
    
    public void addVertex(Vertex aVertex) {
        vertices.add(aVertex);
    }
    
    public void addEdges() {
        for (int i=0; i<vertices.size(); i++) {
                
            for (int j=0; j<vertices.size(); j++) {
                if (vertices.get(j) instanceof Shop && i != j) {
                    vertices.get(i).addEdge(new Edge(vertices.get(i), vertices.get(j)));
                }
            }
        }
    }
    
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
    
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
}
