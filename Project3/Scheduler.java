import java.util.Scanner;
import java.io.FileReader;
import java.awt.Point;

/**
 * A Class to simulation the deliveries
 */
public class Scheduler {
    
    public static void main(String[] args) {
        Scheduler mySim = new Scheduler();
        if ( args.length > 2 )
            mySim.run( args[ 0 ], args[ 1 ], args[ 2 ] );
        else
            mySim.run( args[ 0 ], args[ 1 ] );
        mySim.printTrucks();
        System.out.println( "Number of items not loaded: " + mySim.numOfUnfulfilledSupplies() );
        System.out.println( "All shops satisfied: " + mySim.checkSatisfied() );
        System.out.println( "Max warehouse distance: " + Warehouse.MAX_DISTANCE );
    }
    
    
    /**
     * Method used to run the scheduler with a given max warehouse distance
     * @param shops file name for the shop data
     * @param warehouses file name for the warehouse data
     * @param maxDistance max distance for the trucks to travel from their home warehouse
     */
    public void run( String shops, String warehouses, String maxDistance ) {
        this.shopsFile = shops;
        this.warehousesFile = warehouses;
        this.maxDistance = Integer.parseInt( maxDistance );
        readInData( shops, warehouses );
        addEdges();
        
        Warehouse.MAX_DISTANCE = this.maxDistance;
            
        loadTrucks();
        sendTrucks();
        countTrucks();
    }
    
    
    /**
     * Method used to run the scheduler without a given max warehouse distance
     * @param shops file name for the shop data
     * @param warehouses file name for the warehouse data
     */
    public void run( String shops, String warehouses ) {
        this.shopsFile = shops;
        this.warehousesFile = warehouses;
        readInData( shops, warehouses );
        addEdges();
        
        this.maxDistance = calculateAverageWarehouseDistance();
            
        loadTrucks();
        sendTrucks();
        countTrucks();
    }
    
    
    private String shopsFile;               // file name used for the shop data
    private String warehousesFile;          // file name used for the warehouse data
    private Graph cityGraph;                // graph to represent the city of shops and warehouses
    private Shop[] shops;                   // list of all shops in the city
    private Warehouse[] warehouses;         // list of all warehouses in the city
    private int totalDistanceTravelled;     // total distance travelled by all trucks
    private int totalNumOfTrucks;           // total number of trucks used
    private int maxDistance;                // max distance trucks can travel from their home warehouse
    
    
    /**
     * A Constructor for the Scheduler
     */
    public Scheduler() {
        this.cityGraph = new Graph();
        this.totalDistanceTravelled = 0;
        this.totalNumOfTrucks = 0;
        this.shopsFile = new String();
        this.warehousesFile = new String();
    }
    
    /**
     * A Method to read in the given data
     * @param shopsFileName       The name of the file with the shops
     * @param WarehousesFileName  The name of the file with the warehouses 
     */
    public void readInData(String shopsFileName, String warehousesFileName) {
        Scanner inputShops = null;
        Scanner inputWarehouses = null;
        
        try {
            inputShops = new Scanner(new FileReader(shopsFileName));
            inputWarehouses = new Scanner(new FileReader(warehousesFileName));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        int numOfShops = Integer.parseInt(inputShops.nextLine());
        int numOfWarehouses = Integer.parseInt(inputWarehouses.nextLine());
        
        shops = new Shop[numOfShops];
        int shopIndex = 0;
        int warehouseIndex = 0;
        warehouses = new Warehouse[numOfWarehouses];
        
        
        //Reading in the shops
        while (inputShops.hasNextLine()) {
            String aLine = inputShops.nextLine();
            String[] parts = aLine.split("[\\s\\.,\\(\\)\\:]+");
              
            Point location = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            
            String[] cargo = new String[parts.length-3];
            
            
            for (int i=3; i<parts.length; i++) 
                cargo[i-3] = parts[i];
            

            Shop aShop = new Shop(parts[0], location, cargo);
            cityGraph.addVertex(aShop); 
            shops[shopIndex++] = aShop;
        }
        
        
        
        //Reading in the warehouses
        while (inputWarehouses.hasNextLine()) {
            String aLine = inputWarehouses.nextLine();
            String[] parts = aLine.split("[\\s\\.,\\(\\)\\:]+");
            
            Point location = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            
            int numOfTrucks = Integer.parseInt(parts[3]);
            
            Warehouse aWarehouse = new Warehouse(parts[0], location, numOfTrucks);
            cityGraph.addVertex(aWarehouse);
            warehouses[warehouseIndex++] = aWarehouse;
        }
        
        
    }
    
    
    /**
     * A Method to calculate the average shortest warehouse distance from each shop and sets the max distance in the warehouse class to double that distance
     */
    public int calculateAverageWarehouseDistance() {
        int totalShortestDistance = 0;
        for ( int i = 0; i < this.shops.length; i++ ) {
            int shortestDistance = Vertex.distanceBetween( this.shops[ i ], this.warehouses[ 0 ] );
            for ( int j = 1; j < this.warehouses.length; j++ ) {
                int currentDistance = Vertex.distanceBetween( this.shops[ i ], this.warehouses[ j ] );
                if ( currentDistance < shortestDistance ) {
                    shortestDistance = currentDistance;
                }
            }
            totalShortestDistance += shortestDistance;
        }
        int averageDistance = totalShortestDistance / this.shops.length;
        return Warehouse.MAX_DISTANCE = averageDistance * 2;
    }
    
    
    /**
     * A Method to add an edge from all vertices to all shops in the graph
     */
    public void addEdges() {
        cityGraph.addEdges();
    }
    
    
    /**
     * A Method to load all of the trucks to prepare for deliveries
     */
    public void loadTrucks() {
            // loads trucks for all warehouses except for the base warehouse
        for (int i=0; i<warehouses.length-1; i++) {
            
            while (warehouses[i].hasTrucksAvailable()) {
                warehouses[i].loadTruck();
            }
            
        }
        
            // fulfills all remaining unfulfilled shops by the base warehouse
        Warehouse baseWarehouse = warehouses[ this.warehouses.length-1 ];
        try {
            baseWarehouse.fulfillRemainingShops();
        } catch ( Exception e ) {
            System.out.println( e );
            System.exit(1);
        }
    }
    
    
    /**
     * A Method to send all of the trucks out to make deliveries
     * Also calculates the total distance travelled
     */
    public void sendTrucks() {
        for ( int i = 0; i < this.warehouses.length; i++ ) {
            this.totalDistanceTravelled += this.warehouses[ i ].sendTrucks();
        }
    }
    
    
    /**
     * A Method to print out all of the trucks
     */
    public void printTrucks() {
        int totalTrucks = 0;
        int trucksFromBase = 0;
        for ( int i = 0; i < this.warehouses.length; i++ ) {
            System.out.println( this.warehouses[ i ] );
            totalTrucks += this.warehouses[ i ].getNumOfTrucks();
            if ( i == this.warehouses.length - 1 )
                trucksFromBase = this.warehouses[ i ].getNumOfTrucks();
        }
        this.totalNumOfTrucks = totalTrucks;
        System.out.println( "Total distance travelled by all " + totalTrucks + " trucks: " + this.totalDistanceTravelled );
        System.out.println( "Trucks used from base warehouse: " + trucksFromBase  );
    }
    
    
    /**
     * A Method to count the number of trucks used and the total distance travelled by them
     */
    public void countTrucks() {
        int totalTrucks = 0;
        for ( int i = 0; i < this.warehouses.length; i++ ) {
            totalTrucks += this.warehouses[ i ].getNumOfTrucks();
        }
        this.totalNumOfTrucks = totalTrucks;
    }
    
    
    /**
     * A Method to check if all of the cargos are delivered
     * @return int  Returns the number of cargo's that aren't delivered
     */
    public int numOfUnfulfilledSupplies() {
        int numOfUnfulfilled = 0;
        for ( int i = 0; i < this.shops.length; i++ ) {
            Shop aShop = this.shops[ i ];
            for ( Cargo c : aShop.getSupplyList() ) {
                if ( !c.isLoaded() ) {
                    numOfUnfulfilled++;
                }
            }
        }
        
        return numOfUnfulfilled;
    }
    
    
    /**
     * A method to check if all shops have been fulfilled
     * @return boolean  returns true if all shops are fulfilled, else false
     */
    public boolean checkSatisfied() {
        for ( int i = 0; i < this.shops.length; i++ ) {
            Shop aShop = this.shops[ i ];
            if ( !aShop.isSatisfied() )
                return false;
        }
        
        return true;
    }
    
    
    /**
     * A Method to return the graph of the city
     * @return Graph  Returns the graph of the city
     */
    public Graph getGraph() {
        return cityGraph;
    }
    
    
    /**
     * Returns a string representing the scheduler simulation
     */
    public String toString() {
        return "Experiment using \"" + this.shopsFile + "\" and \"" + this.warehousesFile + "\" resulted in a total distance of " + this.totalDistanceTravelled + " travelled by " + this.totalNumOfTrucks + " trucks. \tMAX_DISTANCE == " + this.maxDistance;
    }
}
