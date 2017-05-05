

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;

/**
 * The test class ShopTest.
 */
public class ShopTest
{
    @Test
    public void testShopCreation() {
        int[] supplies = { 100, 50 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        
        boolean keySatisfied = false;
        boolean actualSatisfied = a.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
        
        int keyNumOfSupplies = 2;
        int actualNumOfSupplies = a.getSupplyList().size();
        assertEquals( keyNumOfSupplies, actualNumOfSupplies );
    }
    
    @Test
    public void testShopSatisfied() {
        int[] supplies = { 100, 50 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        ArrayList< Cargo > list = a.getSupplyList();
        
        for ( Cargo c : list )
            c.setLoaded();
        
        boolean keySatisfied = true;
        boolean actualSatisfied = a.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
    }
}
