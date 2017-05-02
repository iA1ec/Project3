import java.util.ArrayList;
import java.awt.Point;

/**
 * A Class to represent a Shop
 */
public class Shop extends Vertex
{
    private ArrayList< Cargo > supplyList; // set of supplies the shop needs
    private boolean satisfied; // keeps track of whether the shop needs any more supplies
    
    /**
     * Constructor to create a shop with the given id and point of location. Initializes the ArrayList of supplies as empty.
     * @param anId shop's unique id
     * @param coords shop location
     */
    public Shop( Point aLocation, String anId ) {
        super( anId, aLocation );
        this.supplyList = new ArrayList<Cargo>();
        this.satisfied = false;
    }
    
    /**
     * Constructor to create a shop with the given id, point of location, and list of supplies. Adds each position in the supplies array as an individual element in the local supplies ArrayList
     * @param anId shop's unique id
     * @param coords shop location
     * @param supplyList array of the shop's needed supplies
     */
    public Shop( String anId, Point aLocation, int[] supplies )
    {
        super( anId, aLocation );
        this.supplyList = new ArrayList<Cargo>();
        
        for ( int s : supplies )
            this.supplyList.add( new Cargo( this, s ) );
    }
    
    /**
     * Constructor to create a shop with the given id, point of location, and list of supplies. Adds each position in the supplies array as an individual element in the local supplies ArrayList
     * @param anId shop's unique id
     * @param coords shop location
     * @param supplyList array of the shop's needed supplies in string format
     */
    public Shop( String anId, Point aLocation, String[] supplies )
    {
        super( anId, aLocation );
        this.supplyList = new ArrayList<Cargo>();
        
        for ( String s : supplies )
            this.supplyList.add( new Cargo( this, Integer.parseInt( s ) ) );
    }
    
    /**
     * Adds a single set of supplies to the shop's supply list
     * @param supplies weight of added supplies
     */
    public void addSupplies( int supplies ) {
        this.supplyList.add( new Cargo( this, supplies ) );
    }
    
    /**
     * Adds a list of supplies to the shop's supply list
     * @param supplies array of weights of added supplies
     */
    public void addSupplies( int[] supplies ) {
        for ( int s : supplies )
            this.supplyList.add( new Cargo( this, s ) );
    }
    
    /**
     * A Method to test if the shop is satisfied, meaning all the shops cargo's have been loaded
     * @return boolean  Returns true is all the cargos of a shop are loaded, else false
     */
    public boolean isSatisfied() {
        if ( this.satisfied )
            return true;
            
        for (int i=0; i<supplyList.size(); i++) {
            if (!supplyList.get(i).isLoaded())
                return false;
        }
        
        return this.satisfied = true;
    }
     
    /**
     * A Method to get the supply list of the shop
     * @return ArrayList< Cargo >  Returns the list of cargos that this shop needs
     */
    public ArrayList< Cargo > getSupplyList() {
        return supplyList;
    }
}
