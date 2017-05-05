

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;

/**
 * The test class CargoTest.
 */
public class CargoTest
{
    @Test
    public void testCargoCreation() {
        int[] supplies = { 100, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Cargo c = new Cargo( a, 100 );
        
        int keyWeight = 100;
        int actualWeight = c.getWeight();
        assertEquals( keyWeight, actualWeight );
        
        Shop keyShop = a;
        Shop actualShop = c.getDestination();
        assertEquals( keyShop, actualShop );
    }
    
    @Test
    public void testCargoLoaded() {
        int[] supplies = { 100, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Cargo c = new Cargo( a, 100 );
        
        boolean keyLoaded = false;
        boolean actualLoaded = c.isLoaded();
        assertEquals( keyLoaded, actualLoaded );
        
        c.setLoaded();
        
        keyLoaded = true;
        actualLoaded = c.isLoaded();
        assertEquals( keyLoaded, actualLoaded );
    }
}
