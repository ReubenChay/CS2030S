
abstract class Driver implements Comparable<Driver> {

    private final String identifier; 
    private final int waitTime;
    private final CabService[] services;
    private static final int ZERO = 0;

    Driver(String identifier, int waitTime, CabService[] services) {
        this.identifier = identifier; 
        this.waitTime = waitTime; 
        this.services = services; 
    }

    CabService findCheapestService(Request request) {

        CabService cheapestService = this.services[ZERO];
        for (int i = 1; i < services.length; i++) {
            if(services[i].computeFare(request) < cheapestService.computeFare(request)) {
                cheapestService = services[i];
            }
        }
        return cheapestService; 
    }

    @Override 
    public int compareTo(Driver other) {
        if (this.waitTime < other.getWaitTime()) {
            return -1; 
        } else if (this.waitTime > other.getWaitTime()) {
            return 1;
        } else {
            return 0;
        }
    }

    String getIdentifier() {
        return this.identifier; 
    }

    int getWaitTime() {
        return this.waitTime;
    }

    CabService[] getCabServices() {
        return this.services; 
    }
}
