import java.util.ArrayList;
import java.awt.Point;

/**
 * Write a description of class Shop here.
 *
 * @author Alec Flanigan
 * @version 4/20/2017
 */
public class Shop extends Vertex
{
    private ArrayList< Cargo > supplyList; // set of supplies the shop needs
    
    /**
     * Constructor to create a shop with the given id and point of location. Initializes the ArrayList of supplies as empty.
     * @param anId shop's unique id
     * @param coords shop location
     */
    public Shop( Point aLocation, String anId ) {
        super( anId, aLocation );
        this.supplyList = new ArrayList<Integer>();
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
        this.supplyList = new ArrayList<Integer>();
        
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
        this.supplyList = new ArrayList<Integer>();
        
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
    
    public void removeSupplies( Cargo c ) {
        for ( int i = 0; i < this.supplyList.size(); i++ ) {
            if ( this.supplyList.get( i ).equals( c ) )
                this.supplyList.remove( i );
        }
    }
}
