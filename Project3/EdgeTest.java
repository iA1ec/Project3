

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;

/**
 * The test class EdgeTest.
 */
public class EdgeTest
{
    @Test
    public void testEdgeCreation() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        Vertex b = new Vertex( "b", new Point(5, 3) );
        
        Edge e = new Edge( a, b );
        
        Vertex keyStart = a;
        Vertex actualStart = e.getStart();
        assertEquals( keyStart, actualStart );
        
        Vertex keyEnd = b;
        Vertex actualEnd = e.getEnd();
        assertEquals( keyEnd, actualEnd );
        
        int keyDistance = 6;
        int actualDistance = e.getDistance();
        assertEquals( keyDistance, actualDistance );
    }
    
    @Test
    public void testEdgeComparison() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        Vertex b = new Vertex( "b", new Point(5, 3) );
        Vertex c = new Vertex( "c", new Point(4, 2) );
        
        Edge e = new Edge( a, b );
        Edge d = new Edge( c, a );
        
        int keyComparison = 2;
        int actualComparison = e.compareTo( d );
        assertEquals( keyComparison, actualComparison );
    }
}
