import java.awt.Point;
import java.util.ArrayList;

/**
 * A Class to represent a Warehouse with many trucks that has shop supplies
 */
public class Warehouse extends Vertex {
    
    private int numOfTrucks;
    private Truck[] trucks;
    public static final int MAX_DISTANCE = 100;
    
    /**
     * A Constructor given an id, location, and number of trucks
     */
    public Warehouse(String id, Point location, int numOfTrucks) {
        super(id, location);
        this.trucks = new Truck[ numOfTrucks ];
        this.numOfTrucks = 0;
    }
    
    /**
     * A Method to add a truck to the queue of trucks
     * @param a  The number of trucks to be added
     */
    public void addTrucks(int a) {
        numOfTrucks = a;
    }
    
    public void loadTruck() {
        Truck aTruck = new Truck(this);
        this.trucks[ this.numOfTrucks++ ] = aTruck;
        
        Vertex start = this;
        while (aTruck.getWeight() < Truck.MAX_WEIGHT - Truck.CUT_OFF) {
            Shop s = (Shop)findClosestShop( aTruck, start );
            if ( s == null )
                break;
            ArrayList<Cargo> supplies = s.getSupplyList();
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
    
    public Shop findClosestShop( Truck aTruck, Vertex start ) {
        for (int i=0; i < start.getEdges().size(); i++) {
            Vertex v = start.getEdges().get( i ).getEnd();
            if ( !(v instanceof Shop) )
                continue;
            Shop s = (Shop)(start.getEdges().get(i).getEnd());
            if ( !s.isSatisfied() && !aTruck.hasChecked( s ) && ( Vertex.distanceBetween( this, s ) < Warehouse.MAX_DISTANCE ) ) {
            //if ( !s.isSatisfied() && !aTruck.hasChecked( s ) ) {
                return s;
            }
        }
        return null; //all shops are satisfied
    }
    
    public Shop findClosestShopForBase( Truck aTruck, Vertex start ) {
        for (int i=0; i < start.getEdges().size(); i++) {
            Vertex v = start.getEdges().get( i ).getEnd();
            if ( !(v instanceof Shop) )
                continue;
            Shop s = (Shop)(start.getEdges().get(i).getEnd());
            if ( !s.isSatisfied() && !aTruck.hasChecked( s ) ) {
                return s;
            }
        }
        return null; //all shops are satisfied
    }
    
    public void fulfillRemainingShops() {
        Truck aTruck = new Truck( this );
        this.trucks[ this.numOfTrucks++ ] = aTruck;
        Vertex start = this;
        Shop s = null;
        while ( start != null ) {
            if ( aTruck.getWeight() > Truck.MAX_WEIGHT - Truck.CUT_OFF ) {
                    aTruck = new Truck( this );
                    this.trucks[ this.numOfTrucks++ ] = aTruck;
            }
            
            s = findClosestShopForBase( aTruck, start );
            if ( s != null ) {
                ArrayList<Cargo> supplies = s.getSupplyList();
                for (int i=0; i<supplies.size(); i++) {
                    Cargo c = supplies.get(i);
                    if (!c.isLoaded() && c.getWeight() + aTruck.getWeight() <= Truck.MAX_WEIGHT) {
                        aTruck.addWeight(c);
                        c.setLoaded();
                    }
                }
            } else {
                Shop fulfillmentCheck = findClosestShopForBase( new Truck( this ), this );
                if ( fulfillmentCheck != null ) {
                    aTruck = new Truck( this );
                    this.trucks[ this.numOfTrucks++ ] = aTruck;
                    s = fulfillmentCheck;
                } else {
                    return;
                }
            }
            
            start = s;
        }
        System.out.println( "Num of trucks used from base warehouse: " + this.numOfTrucks );
    }
    
    public boolean hasTrucksAvailable() {
        if ( this.numOfTrucks < this.trucks.length )
            return true;
        else
            return false;
    }
    
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
    
    public int getNumOfTrucks() {
        return numOfTrucks;
    }
    
    public String toString() {
        String print = new String();
        for ( int i = 0; i < this.numOfTrucks; i++ ) {
            print += this.trucks[ i ] + "\n";
        }
        return print;
    }
}
