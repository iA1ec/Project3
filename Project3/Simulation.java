import java.util.Scanner;
import java.io.FileReader;
import java.awt.Point;

/**
 * A Class to simulation the deliveries
 */
public class Simulation {
    
    public static void main(String[] args) {
        Simulation mySim = new Simulation();
        mySim.readInData("shops.txt", "warehouses1.txt");
        //mySim.sortWarehouses();
        mySim.addEdges();
        mySim.loadTrucks();
        mySim.sendTrucks();
        mySim.printTrucks();
        System.out.println( "Number of items not loaded: " + mySim.numOfUnfulfilledSupplies() );
        System.out.println( "All shops satisfied: " + mySim.checkSatisfied() );
        //mySim.getGraph().printGraph();
    }
    
    
    private Graph cityGraph;
    private Shop[] shops;
    private Warehouse[] warehouses;
    private int totalDistanceTravelled;
    
    public Simulation() {
        cityGraph = new Graph();
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
        
        
        
        //int minCargo = 500; //Finding the minimum weight of a cargo
        
        //Reading in the shops
        while (inputShops.hasNextLine()) {
            String aLine = inputShops.nextLine();
            String[] parts = aLine.split("[\\s\\.,\\(\\)\\:]+");
            
            //for (String s : parts)
                //System.out.println(s);
                
            Point location = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            
            String[] cargo = new String[parts.length-3];
            
            
            for (int i=3; i<parts.length; i++) {
                cargo[i-3] = parts[i];
                //if (Integer.parseInt(parts[i]) < minCargo)
                    //minCargo = Integer.parseInt(parts[i]);
            }
            
            
            
            Shop aShop = new Shop(parts[0], location, cargo);
            cityGraph.addVertex(aShop); 
            shops[shopIndex++] = aShop;
        }
        
        
        
        //Reading in the warehouses
        while (inputWarehouses.hasNextLine()) {
            String aLine = inputWarehouses.nextLine();
            String[] parts = aLine.split("[\\s\\.,\\(\\)\\:]+");
            //for (String s : parts)
                //System.out.println(s);
            
            Point location = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            
            int numOfTrucks = Integer.parseInt(parts[3]);
            
            Warehouse aWarehouse = new Warehouse(parts[0], location, numOfTrucks);
            cityGraph.addVertex(aWarehouse);
            warehouses[warehouseIndex++] = aWarehouse;
        }
    }
    
    /**
     * Sorts warehouses in order from farthest from the base warehouse to closest
     */
    public void sortWarehouses() {
        for( int p = 1; p < warehouses.length; p++ )
        {
            Warehouse tmp = warehouses[ p ];
            int j = p;

            for( ; j > 0 && tmp.compareTo( warehouses[ j - 1 ] ) < 0; j-- )
                warehouses[ j ] = warehouses[ j - 1 ];
            warehouses[ j ] = tmp;
        }
    }
    
    /**
     * A Method to add an edge from all vertices to all shops in the graph
     */
    public void addEdges() {
        cityGraph.addEdges();
    }
    
    /**
     * A Method to load all of the trucks with the proper weights
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
    
    public void sendTrucks() {
        this.totalDistanceTravelled = 0;
        for ( int i = 0; i < this.warehouses.length; i++ ) {
            this.totalDistanceTravelled += this.warehouses[ i ].sendTrucks();
        }
    }
    
    public void printTrucks() {
        int totalTrucks = 0;
        int trucksFromBase = 0;
        for ( int i = 0; i < this.warehouses.length; i++ ) {
            System.out.println( this.warehouses[ i ] );
            totalTrucks += this.warehouses[ i ].getNumOfTrucks();
            if ( i == this.warehouses.length - 1 )
                trucksFromBase = this.warehouses[ i ].getNumOfTrucks();
        }
        System.out.println( "Total distance travelled by all " + totalTrucks + " trucks: " + this.totalDistanceTravelled );
        System.out.println( "Trucks used from base warehouse: " + trucksFromBase  );
    }
    
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
    
    public boolean checkSatisfied() {
        for ( int i = 0; i < this.shops.length; i++ ) {
            Shop aShop = this.shops[ i ];
            if ( !aShop.isSatisfied() )
                return false;
        }
        
        return true;
    }
    
    public Graph getGraph() {
        return cityGraph;
    }
    
}
