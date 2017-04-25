
/**
 * A Class to simulation the deliveries
 */
public class Simulation {
    
    private Graph cityGraph;
    
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
            inputWarehouses = new Scanner(new FileReader(warehosesFileName));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        while (inputShops.hasNextLine()) {
            String aLine = inputShops.nextLine();
            String[] parts = aLine.split(", ", " ", ")", "(", ":");
            Point location = new Point(parts[1], parts[2]);
            
            String[] cargo = new String[parts.length-3];
            for (int i=3; i<parts.length; i++) {
                cargo[i-3] = parts[i];
            }
            
            cityGraph.addVertex(new Shop(parts[0], location, cargo)); 
        }
           
    }
    
    
}
