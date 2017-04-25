/**
 * A Class to store a single cargo
 */
public class Cargo{
    
   private Shop destination; //The shop where the cargo is going
   private int weight; //The weight of the carg
   
   public Cargo(Shop aShop, int weight) {
       destination = aShop;
       this.weight = weight;
   }
    
   public boolean equals(Cargo aCargo) {
       return destination.equals(aCargo.destination) && weight == aCargo.weight;
   }
   
}
