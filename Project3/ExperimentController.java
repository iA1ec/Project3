
/**
 * Experiment controller for the project
 */
public class ExperimentController
{
    public static void main( String[] args ) {
        ExperimentController ec = new ExperimentController();
        ec.run();
    }
    
    
    /**
     * Runs the experiments for the controller
     */
    public void run() {
            // initializes all experiments
        Scheduler[] experiments = new Scheduler[ 4 ];
        for ( int i = 0; i < experiments.length; i++ )
            experiments[ i ] = new Scheduler();
            
            // runs all experiments
        //experiments[ 0 ].run( "shops.txt", "warehouses1.txt" );
        //experiments[ 1 ].run( "shops.txt", "warehouses2.txt" );
        experiments[ 0 ].run( "testshops1.txt", "testwarehouses1.txt" );
        experiments[ 1 ].run( "testshops1.txt", "testwarehouses2.txt" );
        experiments[ 2 ].run( "testshops2.txt", "testwarehouses1.txt" );
        experiments[ 3 ].run( "testshops2.txt", "testwarehouses3.txt" );
        
        for ( int i = 0; i < experiments.length; i++ ) {
            System.out.println( "Case " + ( i + 1 ) + ": " + experiments[ i ] );
        }
    }
}
