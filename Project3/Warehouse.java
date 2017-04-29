import java.awt.Point;
import java.util.ArrayList;

/**
 * A Class to represent a Warehouse with many trucks that has shop supplies
 */
public class Warehouse extends Vertex {
    
    private int numOfTrucks;
    private Truck[] trucks;
    
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
        
        while (aTruck.getWeight() < Truck.MAX_WEIGHT - Truck.CUT_OFF) {
            Shop s = findClosestShop();
            ArrayList<Cargo> supplies = s.getSupplyList();
            for (int i=0; i<supplies.size(); i++) {
                Cargo c = supplies.get(i);
                if (!c.isLoaded() && c.getWeight() + aTruck.getWeight() <= Truck.MAX_WEIGHT) {
                    aTruck.addWeight(c);
                    c.setLoaded();
                }
            }
               
        }
    }
    
    /**
     * A Method to find the closest Shop that is not already satisfied
     * @return Shop  Returns the closest shop that is not already fully satisfied
     */
    public Shop findClosestShop() {
        for (int i=0; i<edges.size(); i++) {
            Shop s = (Shop)(edges.get(i).getEnd());
            if (!s.isSatisfied()) {
                return s;
            }
        }
        return null; //all shops are satisfied
    }
    
    public void fulfillRemainingShops() {
        Truck aTruck = new Truck( this );
        this.trucks[ this.numOfTrucks++ ] = aTruck;
        
        for (int i=0; i<edges.size(); i++) {
            Shop s = (Shop)(edges.get(i).getEnd());
            ArrayList<Cargo> supplies = s.getSupplyList();
            while (!s.isSatisfied()) {
                
                if ( aTruck.getWeight() > Truck.MAX_WEIGHT - Truck.CUT_OFF ) {
                    aTruck = new Truck( this );
                    this.trucks[ this.numOfTrucks++ ] = aTruck;
                }
                
                for (int j=0; j<supplies.size(); j++) {
                    Cargo c = supplies.get(j);
                    if (!c.isLoaded() && c.getWeight() + aTruck.getWeight() <= Truck.MAX_WEIGHT) {
                        aTruck.addWeight(c);
                        c.setLoaded();
                    }
                }
            }
        }
    }
    
    public boolean hasTrucksAvailable() {
        if ( this.numOfTrucks < this.trucks.length )
            return true;
        else
            return false;
    }
    
    public void sendTrucks() {
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
        }
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
