import java.awt.Point;
import java.util.*;

/**
 * Represents a single vertex
 */
public class Vertex
{
    protected String id;                    // the id associated with the vertex
    protected Point location;               // the location of the vertex in the graph
    protected ArrayList< Edge > edges;      // the edges associated with the vertex
    
    /**
     * Creates a new vertex given an id and a location
     * @param anId id of the vertex
     * @param aLocation location of the vertex represented by the point class
     */
    public Vertex( String anId, Point aLocation ) {
        this.id = anId;
        this.location = aLocation;
        this.edges = new ArrayList< Edge >();
    }
    
    /**
     * Adds a new edge to the vertex
     * @param anEdge edge to add to the vertex
     */
    public void addEdge( Edge anEdge ) {
        int position = Collections.binarySearch(edges, anEdge);
        
        if (position < 0)
            position = (-1 * position) - 1;
        
        this.edges.add( position, anEdge );
    }
    
    /**
     * Calculates the total distance between two vertices
     * @param v1 first vertex
     * @param v2 second vertex
     * @return distance between the two vertices
     */
    public static int distanceBetween( Vertex v1, Vertex v2 ) {
        if ( v1 == v2 )
            return 0;
            
        int xDis = (int)Math.abs( v1.getLocation().getX() - v2.getLocation().getX() );
        int yDis = (int)Math.abs( v1.getLocation().getY() - v2.getLocation().getY() );
        return xDis + yDis;
    }
    
    /**
     * Returns the location of the vertex
     * @return location of the vertex
     */
    public Point getLocation() {
        return this.location;
    }
    
    /**
     * Returns the id of the vertex
     * @return id of the vertex
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Returns a list of all the edges associated with the vertex
     * @return array list of edges
     */
    public ArrayList< Edge > getEdges() {
        return this.edges;
    }
    
    /**
     * Prints all of the edges to the terminal
     */
    public void printEdges() {
        for (Edge e : edges)    
            System.out.print(e.getEnd().getId() + " distance: " + e.getDistance() + ", ");
    }
}
