import java.awt.Point;
import java.util.ArrayList;

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
    
    public void loadTruck() {
        numOfTrucks--;
        Truck aTruck = new Truck(this);
        
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
    
    public int getNumOfTrucks() {
        return numOfTrucks;
    }
    
    
}
