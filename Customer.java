package cs2030.simulator; 

class Customer { 

    private final double arrivalTime; 
    private final double serviceTime; 
    private final int identity; 

    Customer(double arrivalTime, double serviceTime, int identity) {
        this.arrivalTime = arrivalTime; 
        this.serviceTime = serviceTime; 
        this.identity = identity; 
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    int getIdentity() {
        return this.identity; 
    }

    double getServiceTime() {
        return this.serviceTime;
    }
}
