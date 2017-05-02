import java.util.ArrayList;
import java.util.HashSet;

/**
 * A Class to represent a single Truck
 */
public class Truck {
    
    private Warehouse baseWarehouse; //the base warehouse of the Truck
    private ArrayList< Cargo > weights; //The weight that the truck is carrying
    private HashSet< Shop > shopsChecked; // list of shops visited by the truck
    private int totalWeight; //The total weight in the truck
    private int distance; //total distance travelled by truck
    public static final int MAX_WEIGHT = 500; //The maximum weight a truck can hold
    public static final int CUT_OFF = 20; //The truck will stop being loaded if there is only 20 units of weight left
    
    /**
     * A Constructor only given a base warehouse
     */
    public Truck( Warehouse aWarehouse ) {
        baseWarehouse = aWarehouse;
        weights = new ArrayList< Cargo >();
        shopsChecked = new HashSet< Shop >();
        totalWeight = 0;
        distance = 0;
    }
    
    /**
     * A Method to add a weight to the truck, in the form of a cargo
     * @param c  The cargo that is added to the truck
     */
    public void addWeight(Cargo c) {
        if (totalWeight + c.getWeight() > MAX_WEIGHT)
            throw new IllegalArgumentException(); //Truck is too full
            
        weights.add(c);
        totalWeight += c.getWeight();
        if ( !shopsChecked.contains( c.getDestination() ) )
            this.shopsChecked.add( c.getDestination() );
    }
    
    
    /**
     * A Method to see is a truck has "checked" a shop during the loading process
     * @param  s        The shop that the method is checking
     * @return boolean  Returns true is the shop has been checked, else false
     */
    public boolean hasChecked( Shop s ) {
        boolean check = this.shopsChecked.contains( s );
        if ( !check )
            this.shopsChecked.add( s );
        return check;
    }
    
    /**
     * A Method to get the total weight of the truck
     * @return int  Returns the total weight of the truck
     */
    public int getWeight() {
        return totalWeight;
    }
    
    /**
     * A Method to set the distance of this truck
     * @param aDistance  The new distance of the truck
     * @throw IllegalArgumentException if the distance given is negatie
     */
    public void setDistance( int aDistance ) {
        if (aDistance < 0)
            throw new IllegalArgumentException( "Cannot have negative Distance");
            
        this.distance = aDistance;
    }
    
    /**
     * A Method to get the cargos in the truck
     * @return ArrayList< Cargo >  Returns the ArrayList of cargos
     */
    public ArrayList< Cargo > getCargos() {
        return this.weights;
    }
    
    /**
     * A toString method for a truck
     * @return String  Returns a string with all of the trucks information
     */
    public String toString() {
        String print = "Truck comes from warehouse # " + this.baseWarehouse.getId() + " \t Distance Travelled: " + this.distance + " \t Total Weight: " + this.totalWeight + " \t Drop offs: ";
        for ( Cargo c : this.weights ) {
            print += "Shop " + c.getDestination().getId() + " - " + c.getWeight() + ", ";
        }
        return print;
    }
}
