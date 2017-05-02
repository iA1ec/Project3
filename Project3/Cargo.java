/**
 * A Class to store a single cargo
 */
public class Cargo {
    
   private Shop destination; //The shop where the cargo is going
   private int weight; //The weight of the carg
   private boolean loaded; //If the cargo has been loaded onto a truck yet
   
   /**
    * A Constructor for a Cargo
    * @param aShop  The Shop where this cargo is going
    * @param weight The weight of this cargo
    */
   public Cargo(Shop aShop, int weight) {
       destination = aShop;
       this.weight = weight;
       loaded = false;
   }
    
   
   /**
    * A Method to test if two cargos are equals, by weight and destination
    * @param  aCargo  The cargo that the method is comparing to
    * @return boolean Returns true if the cargo's fields are equals, else false
    */
   public boolean equals(Cargo aCargo) {
       return destination.equals(aCargo.destination) && weight == aCargo.weight;
   }
   
   
   /**
    * A Method to ask if the cargo has been loaded
    * @return boolean  Returns true if the cargo has been loaded, else false
    */
   public boolean isLoaded() {
       return loaded;
   }
   
   
   /**
    * A Method to set the cargo to be loaded
    */
   public void setLoaded() {
       loaded = true;
   }
   
   /**
    * A Method to get the weight of the cargo
    * @return int  Returns the weight of the shop
    */
   public int getWeight() {
       return this.weight;
   }
   
   /**
    * A Method to get the destination shop of this cargo
    * @return Shop  Returns the shop where this cargo is going
    */
   public Shop getDestination() {
       return this.destination;
    }
}
