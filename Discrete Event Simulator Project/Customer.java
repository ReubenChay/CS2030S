package cs2030.simulator; 

import java.util.function.Supplier;
import java.util.List; 

class Customer { 

    private final double arrivalTime; 
    private final Supplier<Double> serviceTime; 
    private final int identity; 
    private final List<Double> serveTime; 

    Customer(double arrivalTime, int identity, 
            Supplier<Double> serviceTime, List<Double> serveTime) {
        this.arrivalTime = arrivalTime; 
        this.identity = identity; 
        this.serviceTime = serviceTime; 
        this.serveTime = serveTime;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    int getIdentity() {
        return this.identity; 
    }

    Supplier<Double> getSupp() {
        return this.serviceTime;
    }

    double getServiceTime() {
        if (this.serveTime.get(0) == -1.0) {
            this.serveTime.set(0, serviceTime.get());
        }
        return this.serveTime.get(0); 
    }

    boolean isGreedyCustomer() {
        return false; 
    }

    @Override 
    public String toString() {
        return String.format("%d", this.identity);
    }
}
