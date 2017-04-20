
/**
 * A Class to represent a single Truck
 */
public class Truck {
    
    private Warehouse baseWarehouse; //the base warehouse of the Truck
    private int weight; //The weight that the truck is carrying
    public static final int MAX_WEIGHT = 500; //The maximum weight a truck can hold
    
    /**
     * A Constructor only given a base warehouse, initializes weight to 0
     */
    public Truck(Warehouse aWarehouse) {
        this(aWarehouse, 0);
    }
    
    /**
     * A Constructor given an initial weight and a warehouse
     */
    public Truck(Warehouse aWarehouse, int weight) {
        baseWarehouse = aWarehouse;
        if (weight >= 0 && weight <= MAX_WEIGHT)
            this.weight = weight;
        else
            throw new IllegalArgumentException();
    }
    
    
}
