import java.awt.Point;

/**
 * A Class to represent a Warehouse with many trucks that has shop supplies
 */
public class Warehouse extends Vertex {
    
    private int numOfTrucks;
    
    /**
     * A Constructor given an id, location, and number of trucks
     */
    public Warehouse(String id, Point location, int numOfTrucks) {
        super(id, location);
        this.numOfTrucks = numOfTrucks;
    }
    
    /**
     * A Method to add a truck to the queue of trucks
     * @param a  The number of trucks to be added
     */
    public void addTrucks(int a) {
        numOfTrucks = a;
    }
    
    
}
