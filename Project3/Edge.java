
/**
 * Represents an edge between to vertices
 */
public class Edge implements Comparable< Edge >
{
    private Vertex start, end;      // stores the start and end vertex of the edge
    private int distance;           // stores the distance from the first vertex to the second
    
    /**
     * Creates an edge given two vertices
     * @param s starting vertex
     * @param e ending vertex
     */
    public Edge( Vertex s, Vertex e ) {
        this.start = s;
        this.end = e;
        int xDis = (int)Math.abs( s.getLocation().getX() - e.getLocation().getX() );
        int yDis = (int)Math.abs( s.getLocation().getY() - e.getLocation().getY() );
        this.distance = xDis + yDis;
    }
    
    /**
     * Returns the starting vertex of the edge
     * @return start vertex
     */
    public Vertex getStart() {
        return this.start;
    }
    
    /**
     * Returns the ending vertex of the edge
     * @return end vertex
     */
    public Vertex getEnd() {
        return this.end;
    }
    
    /**
     * Returns the distance between the start and end vertex
     * @return the distance of the edge
     */
    public int getDistance() {
        return this.distance;
    }
    
    /**
     * Compares two edges by their associated distances
     * @param  second second edge to compare against
     * @return the distance of this edge minus the distance of the second
     */
    public int compareTo( Edge second ) {
        return this.distance - second.distance;
    }
}
