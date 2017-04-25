
/**
 * Write a description of class Edge here.
 *
 * @author Alec Flanigan
 * @version 4/25/2017
 */
public class Edge implements Comparable< Edge >
{
    private Vertex start, end;
    private int distance;
    
    public Edge( Vertex s, Vertex e ) {
        this.start = s;
        this.end = e;
        int xDis = (int)Math.abs( s.getLocation().getX() - e.getLocation().getX() );
        int yDis = (int)Math.abs( s.getLocation().getY() - e.getLocation().getY() );
        this.distance = xDis + yDis;
    }
    
    public Vertex getStart() {
        return this.start;
    }
    
    public Vertex getEnd() {
        return this.end;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public int compareTo( Edge second ) {
        return this.distance - second.distance;
    }
}
