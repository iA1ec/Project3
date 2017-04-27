import java.util.ArrayList;

/**
 * A Class to represent a single Truck
 */
public class Truck {
    
    private Warehouse baseWarehouse; //the base warehouse of the Truck
    private ArrayList<Integer> weights; //The weight that the truck is carrying
    private int totalWeight; //The total weight in the truck
    public static final int MAX_WEIGHT = 500; //The maximum weight a truck can hold
    
    /**
     * A Constructor only given a base warehouse, initializes weight to 0
     */
    public Truck(Warehouse aWarehouse) {
        baseWarehouse = aWarehouse;
        weights = new ArrayList<Integer>();
        totalWeight = 0;
    }
    
    
    /**
     * A Method to add a weight to the truck
     * @param weight  The new weight to be added to the truck
     */
    public void addWeight(int weight) {
        if (totalWeight + weight > MAX_WEIGHT)
            throw new IllegalArgumentException(); //Truck is too full
            
        weights.add(weight);
        totalWeight += weight;
    }
    
    
}
