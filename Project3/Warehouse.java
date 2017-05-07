import java.awt.Point;
import java.util.ArrayList;

/**
 * A Class to represent a Warehouse with many trucks
 */
public class Warehouse extends Vertex {
    
    private int numOfTrucks; //The number of trucks that have been initialized at this warehouse
    private Truck[] trucks; //The actual truck objects from this warehouse
    private boolean hasTrucksAvailable; //Whether the warehouse has trucks available or not
    public static int MAX_DISTANCE = 60; //The maximum distance from the warehouse a truck can travel, except the base warehouse
    
    /**
     * A Constructor given an id, location, and the maximum number of trucks
     */
    public Warehouse(String id, Point location, int maxNumOfTrucks) {
        super(id, location);
        this.trucks = new Truck[ maxNumOfTrucks ];
        this.numOfTrucks = 0;
        this.hasTrucksAvailable = true;
    }
    
    /**
     * A Method to load a truck in this warehouse, preparing for deliveries
     * This method is not used on the base warehouse
     */
    public void loadTruck() {
        if ( findClosestShop( new Truck( this ), this ) == null ) {
            this.hasTrucksAvailable = false;
            return;
        }
            
        Truck aTruck = new Truck(this);
        this.trucks[ this.numOfTrucks++ ] = aTruck;
        
        Vertex start = this;
        
            // Adding cargo to the truck until there it is full
        while (aTruck.getWeight() < Truck.MAX_WEIGHT - Truck.CUT_OFF) {
            Shop s = findClosestShop( aTruck, start );
            if ( s == null )
                break; //all shops are satisfied
                
            ArrayList<Cargo> supplies = s.getSupplyList();
            
                // Load as many supplies from this shop as possible
            for (int i=0; i<supplies.size(); i++) {
                Cargo c = supplies.get(i);
                if (!c.isLoaded() && c.getWeight() + aTruck.getWeight() <= Truck.MAX_WEIGHT) {
                    aTruck.addWeight(c);
                    c.setLoaded();
                }
            }
            start = s;
        }
    }
    
    
    /**
     * A Method to find the closest shop from a given vertex that is not already satisfied
     * This method is not used for the base warehouse
     * @param  aTruck  The current truck that is being filled
     * @param  start   The Vertex (Shop or Warehouse) that the method starts at
     * @return Shop    Returns the Shop closest to the given vertex that is not already satisfied
     */
    public Shop findClosestShop( Truck aTruck, Vertex start ) {
            // Going through all of the edges of the start vertex, which are ordered by length
        for (int i=0; i < start.getEdges().size(); i++) {
            Vertex v = start.getEdges().get( i ).getEnd();
            
            if ( !(v instanceof Shop) )
                continue;
                
            Shop s = (Shop)(start.getEdges().get(i).getEnd());
            
                // Returns the Shop if it is not satisfied, if it has not been checked already, and if it is not too far away
            if ( !s.isSatisfied() && !aTruck.hasChecked( s ) && ( Vertex.distanceBetween( this, s ) <= Warehouse.MAX_DISTANCE ) ) {
                return s;
            }
        }
        
        return null; //all shops are satisfied
    }
    
    
    /**
     * A Method to find the closest shop from the base warehouse
     * Similar to findClosestShop, except doesn't check distance from base warehouse
     * @param  aTruck  The current truck that is being filled
     * @param  start   The Vertex (Shop or Warehouse) that the method starts at
     * @return Shop    Returns the Shop closest to the given vertex that is not already satisfied
     */
    public Shop findClosestShopForBase( Truck aTruck, Vertex start ) {
            // Going through all of the edges of the start vertex, which are ordered by length
        for (int i=0; i < start.getEdges().size(); i++) {
            Vertex v = start.getEdges().get( i ).getEnd();
            
            if ( !(v instanceof Shop) )
                continue;
                
            Shop s = (Shop)(start.getEdges().get(i).getEnd());
            
                // Returns the Shop if it is not satisfied, if it has not been checked already, and if it is not too far away
            if ( !s.isSatisfied() && !aTruck.hasChecked( s ) ) {
                return s;
            }
        }
        
        return null; //all shops are satisfied
    }
    
    
    /**
     * A Method to use the base warehouse to fulfill all remaining shops
     * @throws Exception if the warehouse runs out of trucks
     */
    public void fulfillRemainingShops() throws Exception {
        if ( findClosestShopForBase( new Truck( this ), this ) == null ) {
            this.hasTrucksAvailable = false;
            return;
        }
            
        Truck aTruck = new Truck( this );
        this.trucks[ this.numOfTrucks++ ] = aTruck;
        Vertex start = this;
        Shop s = s = findClosestShopForBase( aTruck, start );
        
            // runs a loop until the warehouse runs out of trucks or all shops are satisfied
        while ( true ) {
                // creates a new truck if the current truck is past the weight cut-off
            if ( aTruck.getWeight() > Truck.MAX_WEIGHT - Truck.CUT_OFF ) {
                aTruck = new Truck( this );
                this.trucks[ this.numOfTrucks++ ] = aTruck;
            }
            
                // adds weight from the current shop to the truck
            ArrayList<Cargo> supplies = s.getSupplyList();
            for ( int i=0; i<supplies.size(); i++ ) {
                Cargo c = supplies.get( i );
                if ( !c.isLoaded() && c.getWeight() + aTruck.getWeight() <= Truck.MAX_WEIGHT ) {
                    aTruck.addWeight( c );
                    c.setLoaded();
                }
            }
            s = findClosestShopForBase( aTruck, start );
            
                // if the truck has checked to visit all shops
            if ( s == null) {
                Shop fulfillmentCheck = findClosestShopForBase( new Truck( this ), this );
                
                    // creates a new truck if the current truck has checked all shops and not all shops have been satisfied
                if ( fulfillmentCheck != null && this.numOfTrucks < this.trucks.length ) {
                    aTruck = new Truck( this );
                    this.trucks[ this.numOfTrucks++ ] = aTruck;
                    s = fulfillmentCheck;
                    
                    // if all shops have been satisfied, return
                } else if ( fulfillmentCheck == null ) {
                    return;
                    
                    // if the warehouse runs out of trucks, throw an expection
                } else {
                    throw new Exception( "Not enough trucks at base warehouse. Try lowering optimization." );
                }
            }
            
            start = s;
        }
    }
    
    
    /**
     * A Method to check if the Warehouse can create an more trucks
     * @return boolean  Returns true if the warehouse can make more trucks, else false
     */
    public boolean hasTrucksAvailable() {
        if ( !this.hasTrucksAvailable )
            return false;
            
        if ( this.numOfTrucks < this.trucks.length )
            return true;
        else
            return false;
    }
    
    
    /**
     * A Method to send the trucks from this warehouse out to make deliveries
     * @return int  Returns the total distance travelled by trucks from this warehouse
     */
    public int sendTrucks() {
        int totalWarehouseDistance = 0;
        
        for ( int i = 0; i < this.numOfTrucks; i++ ) {
            int distance = 0;
            Truck aTruck = this.trucks[ i ];
            Vertex lastVertex = this;
            
            for ( Cargo c : aTruck.getCargos() ) {
                distance += Vertex.distanceBetween( lastVertex, c.getDestination() );
                lastVertex = c.getDestination();
            }
            
            distance += Vertex.distanceBetween( lastVertex, this ); //going back to Warehouse
            aTruck.setDistance( distance );
            totalWarehouseDistance += distance;
        }
        
        return totalWarehouseDistance;
    }
    
    
    /**
     * A Method to get the number of trucks at this warehouse
     * @return int  Returns the number of trucks from this warehouse
     */
    public int getNumOfTrucks() {
        return numOfTrucks;
    }
    
    
    /**
     * A toString method for printing out a String
     * @return String  Returns the warehouse represented as a string
     */
    public String toString() {
        String print = new String();
        for ( int i = 0; i < this.numOfTrucks; i++ ) {
            print += this.trucks[ i ] + "\n";
        }
        return print;
    }
}
