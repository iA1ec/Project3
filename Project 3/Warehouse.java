import java.awt.Point;

/**
 * A Class to represent a Warehouse with many trucks that has shop supplies
 */
public class Warehouse extends Vertex {
    
    private int numOfTrucks;
    
    /**
     * A Constructor given an id and location
     */
    public Warehouse(int id, Point location) {
        super(id, location);
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
