import java.awt.Point;

/**
 * A Class to represent a Warehouse with many trucks that has shop supplies
 */
public class Warehouse {
    
    private String id; //The ID of this warehouse
    private Point location; //The location of the warehouse
    private int numOfTrucks;
    
    /**
     * A Constructor given an id and location
     */
    public Warehouse(String id, Point location) {
        this.id = id;
        this.location = location;
        numOfTrucks = 0;
    }
    
    /**
     * A Method to add a truck to the queue of trucks
     * @param a  The number of trucks to be added
     */
    public void addTrucks(int a) {
        numOfTrucks = a;
    }
    
    
}
