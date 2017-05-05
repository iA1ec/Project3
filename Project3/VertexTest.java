

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;

/**
 * The test class VertexTest.
 */
public class VertexTest
{
    @Test
    public void testVertexCreation() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        
        String keyId = "a";
        String actualId = a.getId();
        assertEquals( keyId, actualId );
        
        int keyX = 1;
        int actualX = (int)a.getLocation().getX();
        assertEquals( keyX, actualX );
        
        int keyY = 1;
        int actualY = (int)a.getLocation().getY();
        assertEquals( keyY, actualY );
    }
    
    @Test
    public void testVertexDistance() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        Vertex b = new Vertex( "b", new Point(5, 3) );
        
        int key = 6;
        int actual = Vertex.distanceBetween( a, b );
        assertEquals( key, actual );
    }
    
    @Test
    public void testVertexDistanceZero() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        Vertex b = new Vertex( "b", new Point(1, 1) );
        
        int key = 0;
        int actual = Vertex.distanceBetween( a, b );
        assertEquals( key, actual );
    }
    
    @Test
    public void testVertexAddEdge() {
        Vertex a = new Vertex( "a", new Point(1, 1) );
        Vertex b = new Vertex( "b", new Point(5, 3) );
        
        Edge e = new Edge( a, b );
        a.addEdge( e );
        b.addEdge( e );
        
        ArrayList< Edge > edges = a.getEdges();
        Edge d = edges.get( 0 );
        
        Vertex keyStart = a;
        Vertex actualStart = d.getStart();
        assertEquals( keyStart, actualStart );
        
        Vertex keyEnd = b;
        Vertex actualEnd = d.getEnd();
        assertEquals( keyEnd, actualEnd );
        
        int keyDistance = 6;
        int actualDistance = d.getDistance();
        assertEquals( keyDistance, actualDistance );
    }
}
