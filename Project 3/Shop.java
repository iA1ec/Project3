import java.util.ArrayList;
import java.awt.Point;

/**
 * Write a description of class Shop here.
 *
 * @author Alec Flanigan
 * @version 4/20/2017
 */
public class Shop
{
    private String id; // unique id of the shop
    private Point location; // first position is x coord, second postition is y coord
    private ArrayList<Integer> supplyList; // set of supplies the shop needs
    
    /**
     * Constructor to create a shop with the given id and point of location. Initializes the ArrayList of supplies as empty.
     * @param anId shop's unique id
     * @param coords shop location
     */
    public Shop( String anId, Point coords ) {
        this.id = anId;
        this.location = coords;
        this.supplyList = new ArrayList<Integer>();
    }
    
    /**
     * Constructor to create a shop with the given id, point of location, and list of supplies. Adds each position in the supplies array as an individual element in the local supplies ArrayList
     * @param anId shop's unique id
     * @param coords shop location
     * @param supplyList array of the shop's needed supplies
     */
    public Shop( String anId, Point coords, int[] supplies )
    {
        this.id = anId;
        this.location = coords;
        this.supplyList = new ArrayList<Integer>();
        
        for ( int s : supplies )
            this.supplyList.add( new Integer( s ) );
    }
    
    /**
     * Adds a single set of supplies to the shop's supply list
     * @param supplies weight of added supplies
     */
    public void addSupplies( int supplies ) {
        this.supplyList.add( supplies );
    }
    
    /**
     * Adds a list of supplies to the shop's supply list
     * @param supplies array of weights of added supplies
     */
    public void addSupplies( int[] supplies ) {
        for ( int s : supplies )
            this.supplyList.add( s );
    }
}
