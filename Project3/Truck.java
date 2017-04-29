import java.util.ArrayList;
import java.util.HashSet;

/**
 * A Class to represent a single Truck
 */
public class Truck {
    
    private Warehouse baseWarehouse; //the base warehouse of the Truck
    private ArrayList<Cargo> weights; //The weight that the truck is carrying
    private HashSet< Shop > shopsVisited; // list of shops visited by the truck
    private int totalWeight; //The total weight in the truck
    private int distance; //total distance travelled by truck
    public static final int MAX_WEIGHT = 500; //The maximum weight a truck can hold
    public static final int CUT_OFF = 25; //The truck will stop being loaded if there is only 10 units of space left
    
    /**
     * A Constructor only given a base warehouse, initializes weight to 0
     */
    public Truck(Warehouse aWarehouse) {
        baseWarehouse = aWarehouse;
        weights = new ArrayList<Cargo>();
        shopsVisited = new HashSet< Shop >();
        totalWeight = 0;
        distance = 0;
    }
    
    /**
     * A Method to add a weight to the truck
     * @param weight  The new weight to be added to the truck
     */
    public void addWeight(Cargo c) {
        if (totalWeight + c.getWeight() > MAX_WEIGHT)
            throw new IllegalArgumentException(); //Truck is too full
            
        weights.add(c);
        totalWeight += c.getWeight();
        if ( !shopsVisited.contains( c.getDestination() ) )
            this.shopsVisited.add( c.getDestination() );
    }
    
    public boolean hasVisited( Vertex v ) {
        return this.shopsVisited.contains( v );
    }
    
    public int getWeight() {
        return totalWeight;
    }
    
    public void setDistance( int aDistance ) {
        this.distance = aDistance;
    }
    
    public ArrayList< Cargo > getCargos() {
        return this.weights;
    }
    
    public String toString() {
        String print = "Truck comes from warehouse # " + this.baseWarehouse.getId() + " Distance Travelled: " + this.distance + "\t Drop offs: ";
        for ( Cargo c : this.weights ) {
            print += "Shop " + c.getDestination().getId() + " - " + c.getWeight() + ", ";
        }
        return print;
    }
}
