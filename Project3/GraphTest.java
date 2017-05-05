

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;

/**
 * The test class GraphTest.
 */
public class GraphTest
{
    @Test
    public void testGraphCreation() {
        Graph g = new Graph();
        
        int keyNumOfVertices = 0;
        int actualNumOfVertices = g.getVertices().size();
        assertEquals( keyNumOfVertices, actualNumOfVertices );
    }
    
    @Test
    public void testGraphAddEdges() {
        Graph g = new Graph();
        int[] supplies = { 100, 50 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        
        g.addVertex( a );
        g.addVertex( b );
        g.addVertex( w );
        
        int keyNumOfVertices = 3;
        int actualNumOfVertices = g.getVertices().size();
        assertEquals( keyNumOfVertices, actualNumOfVertices );
        
        g.addEdges();
        
        int keyNumOfEdges = 2;
        int actualNumOfEdges = w.getEdges().size();
        assertEquals( keyNumOfEdges, actualNumOfEdges );
        
        keyNumOfEdges = 1;
        actualNumOfEdges = a.getEdges().size();
        assertEquals( keyNumOfEdges, actualNumOfEdges );
        
        keyNumOfEdges = 1;
        actualNumOfEdges = b.getEdges().size();
        assertEquals( keyNumOfEdges, actualNumOfEdges );
    }
}
