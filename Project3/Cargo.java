/**
 * A Class to store a single cargo
 */
public class Cargo{
    
   private Shop destination; //The shop where the cargo is going
   private int weight; //The weight of the carg
   private boolean loaded; //If the cargo has been loaded onto a truck yet
   
   public Cargo(Shop aShop, int weight) {
       destination = aShop;
       this.weight = weight;
       loaded = false;
   }
    
   public boolean equals(Cargo aCargo) {
       return destination.equals(aCargo.destination) && weight == aCargo.weight;
   }
   
   public boolean isLoaded() {
       return loaded;
   }
   
   public void setLoaded() {
       loaded = true;
    }
   
   public int getWeight() {
       return this.weight;
   }
   
   public Shop getDestination() {
       return this.destination;
    }
}
