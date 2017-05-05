

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;

/**
 * The test class TruckTest.
 */
public class TruckTest
{
    @Test
    public void testTruckCreation() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        Truck t = new Truck( w );
        
        int keyWeight = 0;
        int actualWeight = t.getWeight();
        assertEquals( keyWeight, actualWeight );
    }
    
    @Test
    public void testTruckHasVisited() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 100, 100 };
        Shop a = new Shop( "a", new Point(1, 1), supplies );
        Truck t = new Truck( w );
        
        boolean keyChecked = false;
        boolean actualChecked = t.hasChecked( a );
        assertEquals( keyChecked, actualChecked );
        
        keyChecked = true;
        actualChecked = t.hasChecked( a );
        assertEquals( keyChecked, actualChecked );
    }
    
    @Test
    public void testTruckAddWeight() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 100, 100 };
        Shop a = new Shop( "a", new Point(1, 1), supplies );
        Cargo c = new Cargo( a, 100 );
        Truck t = new Truck( w );
        
        t.addWeight( c );
        
        int keyWeight = 100;
        int actualWeight = t.getWeight();
        assertEquals( keyWeight, actualWeight );
        
        boolean keyChecked = true;
        boolean actualChecked = t.hasChecked( a );
        assertEquals( keyChecked, actualChecked );
    }
    
    @Test
    public void testTruckAddWeightMultiple() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 100, 100 };
        Shop a = new Shop( "a", new Point(1, 1), supplies );
        Cargo c = new Cargo( a, 100 );
        Cargo d = new Cargo( a, 300 );
        Truck t = new Truck( w );
        
        t.addWeight( c );
        t.addWeight( d );
        
        int keyWeight = 400;
        int actualWeight = t.getWeight();
        assertEquals( keyWeight, actualWeight );
        
        boolean keyChecked = true;
        boolean actualChecked = t.hasChecked( a );
        assertEquals( keyChecked, actualChecked );
    }
}
