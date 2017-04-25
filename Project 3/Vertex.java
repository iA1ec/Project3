import java.awt.Point;
import java.util.ArrayList;

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
        this.edges.add( anEdge );
    }
    
    public void sortEdges() {
        this.edges.sort( null );
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
