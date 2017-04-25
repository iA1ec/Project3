import java.awt.Point;
import java.util.*;

/**
 * Write a description of class Vertex here.
 *
 * @author Alec Flanigan
 * @version 4/25/2017
 */
public class Vertex
{
    protected String id;
    protected Point location;
    protected ArrayList< Edge > edges;
    
    public Vertex( String anId, Point aLocation ) {
        this.id = anId;
        this.location = aLocation;
        this.edges = new ArrayList< Edge >();
    }
    
    public void addEdge( Edge anEdge ) {
        int position = Collections.binarySearch(edges, anEdge);
        
        //Position is >= 0 if duplicate exists
        if (position < 0) 
            position = (-1 * position) - 1; //correct insert point
        edges.add(position, anEdge);
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public String getId() {
        return this.id;
    }
    
    public ArrayList< Edge > getEdges() {
        return this.edges;
    }
}
