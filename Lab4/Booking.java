
class Booking implements Comparable<Booking> { 

    private final Driver driver; 
    private final Request request; 
    private static final int ZERO = 0;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request; 
    }

    /**

      CabService findLowestFare() {
      CabService[] services = this.driver.getCabServices();
      CabService cheapestService = services[ZERO];

      for (int i = 1; i < services.length; i++) {
      if (services[i].computeFare(this.request) < cheapestService.computeFare(this.request)) {
      cheapestService = services[i]; 
      }
      }
      return cheapestService; 
      }
      */

    @Override 
    public int compareTo(Booking other) {

        if (this.driver.findCheapestService(this.request).
                computeFare(this.request) < other.driver.findCheapestService(other.request).computeFare(other.request)) {
            return -1; 
        } else if (this.driver.findCheapestService(this.request).computeFare(this.request) > 
                other.driver.findCheapestService(other.request).computeFare(other.request)) {
            return 1;
        } else {
            return this.driver.compareTo(other.driver);
        }
    }



    @Override 
    public String toString() {
        String output = ""; 
        output += String.format("$%.2f ", this.driver.findCheapestService(this.request).computeFare(this.request)/(double) 100);
        output += "using ";
        output += this.driver.toString();
        output += " (";
        output += this.driver.findCheapestService(this.request).toString();
        output += ")";

        return output;
    }


}




