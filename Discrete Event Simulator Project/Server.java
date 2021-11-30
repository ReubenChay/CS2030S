package cs2030.simulator; 

import java.util.function.Supplier; 
import java.util.Arrays;
import java.util.List; 

class Server {

    private final int identity; 
    private final boolean isIdle; 
    private final List<Customer> queueList; 
    private final int maxQueue; 
    private final double doneServingTime;
    private final double restingThreshold; 
    private final Supplier<Double> restTime; 
    private final Supplier<Double> restProb; 


    Server(int identity, boolean isIdle, int maxQueue, List<Customer> queueList,
            double doneServingTime, double restingThreshold, 
            Supplier<Double> restTime, Supplier<Double> restProb) {
        this.identity = identity;
        this.isIdle = isIdle; 
        this.maxQueue = maxQueue; 
        this.queueList = queueList; 
        this.doneServingTime = doneServingTime; 
        this.restingThreshold = restingThreshold;
        this.restTime = restTime; 
        this.restProb = restProb; 
    }

    double getRestingThreshold() {
        return this.restingThreshold;
    }

    double getRestTime() {
        return this.restTime.get();
    }

    boolean checkIsIdle() {
        return this.isIdle; 
    }

    boolean checkHasQ() {
        return queueList.size() == maxQueue;
    }

    int getMaxQueue() {
        return this.maxQueue; 
    }

    boolean getIdleStatus() {
        return this.isIdle; 
    }

    int getCurrentQueue() {
        return this.queueList.size();
    }

    List<Customer> getQueueList() {
        return this.queueList; 
    }

    int getDefaultIdentity() {
        return this.identity;
    }

    double getDoneServingTime() {
        return this.doneServingTime; 
    }

    boolean isSelfCheckout() {
        return false; 
    }

    double getQueueTimings() {
        double queueTimings = 0;
        for (Customer c : this.queueList) {
            queueTimings += c.getServiceTime();
        }
        return queueTimings;
    }

    int getIdentity() {
        return this.identity;
    }

    Supplier<Double> getSupplier() {
        return this.restTime; 
    }

    Supplier<Double> getRestProbSupplier() {
        return this.restProb; 
    }

    double getActualRestProb() {
        return this.restProb.get();
    }

    Customer nextCustomer() {
        if (this.queueList.size() > 0) {
            return this.queueList.get(0);
        } else {
            return new Customer(-1, -1, () -> 0.0, Arrays.asList(-1.0));
        }
    }

}
