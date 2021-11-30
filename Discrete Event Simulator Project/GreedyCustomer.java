package cs2030.simulator;

import java.util.function.Supplier;
import java.util.List; 

class GreedyCustomer extends Customer { 

    GreedyCustomer(double arrivalTime, int identity, 
            Supplier<Double> serviceTime, List<Double> serveTime) {
        super(arrivalTime, identity, serviceTime, serveTime);
    }

    @Override 
    public String toString() {
        return super.getIdentity() + "(greedy)"; 
    }

    boolean isGreedyCustomer() {
        return true; 
    }


}




