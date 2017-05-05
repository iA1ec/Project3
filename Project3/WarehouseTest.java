

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;

/**
 * The test class WarehouseTest.
 */
public class WarehouseTest
{
    @Test
    public void testWarehouseCreation() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        
        int keyNumOfTrucks = 0;
        int actualNumOfTrucks = w.getNumOfTrucks();
        assertEquals( keyNumOfTrucks, actualNumOfTrucks );
        
        boolean keyTrucksAvailable = true;
        boolean actualTrucksAvailable = w.hasTrucksAvailable();
        assertEquals( keyTrucksAvailable, actualTrucksAvailable );
    }
    
    @Test
    public void testWarehouseFindClosestShop() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 50, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        Shop keyShop = a;
        Shop actualShop = w.findClosestShop( new Truck( w ), w );
        assertEquals( keyShop, actualShop );
        
        keyShop = a;
        actualShop = w.findClosestShopForBase( new Truck( w ), w );
        assertEquals( keyShop, actualShop );
    }
    
    @Test
    public void testWarehouseFindClosestShopPastMaxDistance() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 50, 100 };
        Shop a = new Shop( "a", new Point(101, 101), supplies );
        Shop b = new Shop( "b", new Point(201, 201), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        Shop keyShop = null;
        Shop actualShop = w.findClosestShop( new Truck( w ), w );
        assertEquals( keyShop, actualShop );
        
        keyShop = a;
        actualShop = w.findClosestShopForBase( new Truck( w ), w );
        assertEquals( keyShop, actualShop );
    }
    
    @Test
    public void testWarehouseLoadTruck() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 50, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        w.loadTruck();
        
        boolean keySatisfied = true;
        boolean actualSatisfied = a.isSatisfied() && b.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
        
        int keyNumOfTrucks = 1;
        int actualNumOfTrucks = w.getNumOfTrucks();
        assertEquals( keyNumOfTrucks, actualNumOfTrucks );
        
        boolean keyAvailableTrucks = false;
        boolean actualAvailableTrucks = w.hasTrucksAvailable();
        assertEquals( keyAvailableTrucks, actualAvailableTrucks );
    }
    
    @Test
    public void testWarehouseLoadTruckUsingMultipleTrucks() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 3 );
        int[] supplies = { 400, 150 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        while ( w.hasTrucksAvailable() )
            w.loadTruck();
        
        boolean keySatisfied = true;
        boolean actualSatisfied = a.isSatisfied() && b.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
        
        int keyNumOfTrucks = 3;
        int actualNumOfTrucks = w.getNumOfTrucks();
        assertEquals( keyNumOfTrucks, actualNumOfTrucks );
        
        boolean keyAvailableTrucks = false;
        boolean actualAvailableTrucks = w.hasTrucksAvailable();
        assertEquals( keyAvailableTrucks, actualAvailableTrucks );
    }
    
    @Test
    public void testWarehouseLoadTruckWithShopsUnfulfilled() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 500, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        w.loadTruck();
        
        boolean keySatisfied = false;
        boolean actualSatisfied = a.isSatisfied() && b.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
        
        int keyNumOfTrucks = 1;
        int actualNumOfTrucks = w.getNumOfTrucks();
        assertEquals( keyNumOfTrucks, actualNumOfTrucks );
        
        boolean keyAvailableTrucks = false;
        boolean actualAvailableTrucks = w.hasTrucksAvailable();
        assertEquals( keyAvailableTrucks, actualAvailableTrucks );
    }
    
    @Test
    public void testWarehouseFulfillRemainingShops() throws Exception {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 50, 100 };
        Shop a = new Shop( "a", new Point(101, 101), supplies );
        Shop b = new Shop( "b", new Point(201, 201), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        w.fulfillRemainingShops();
        
        boolean keySatisfied = true;
        boolean actualSatisfied = a.isSatisfied() && b.isSatisfied();
        assertEquals( keySatisfied, actualSatisfied );
        
        int keyNumOfTrucks = 1;
        int actualNumOfTrucks = w.getNumOfTrucks();
        assertEquals( keyNumOfTrucks, actualNumOfTrucks );
        
        boolean keyAvailableTrucks = false;
        boolean actualAvailableTrucks = w.hasTrucksAvailable();
        assertEquals( keyAvailableTrucks, actualAvailableTrucks );
    }
    
    @Test
    public void testWarehouseSendTrucks() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 1 );
        int[] supplies = { 50, 100 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        while ( w.hasTrucksAvailable() )
            w.loadTruck();
        
        int keyDistance = 12;
        int actualDistance = w.sendTrucks();
        assertEquals( keyDistance, actualDistance );
    }
    
    @Test
    public void testWarehouseSendTrucksMultipleTrucks() {
        Warehouse w = new Warehouse( "w", new Point(1, 1), 3 );
        int[] supplies = { 400, 150 };
        Shop a = new Shop( "a", new Point(2, 2), supplies );
        Shop b = new Shop( "b", new Point(4, 4), supplies );
        
        w.addEdge( new Edge( w, b ) );
        w.addEdge( new Edge( w, a ) );
        a.addEdge( new Edge( a, b ) );
        b.addEdge( new Edge( b, a ) );
        
        while ( w.hasTrucksAvailable() )
            w.loadTruck();
        
        int keyDistance = 28;
        int actualDistance = w.sendTrucks();
        assertEquals( keyDistance, actualDistance );
    }
}
