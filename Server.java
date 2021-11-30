package cs2030.simulator; 
import java.util.function.Supplier; 
import java.util.List; 

class Server {

    private final int identity; 
    private final boolean isIdle; 
    private final List<Customer> queueList; 
    private final int maxQueue; 
    private final double doneServingTime;
    private final Supplier<Double> supp; 


    Server(int identity, boolean isIdle, int maxQueue, List<Customer> queueList,
            double doneServingTime, Supplier<Double> supp) {
        this.identity = identity;
        this.isIdle = isIdle; 
        this.maxQueue = maxQueue; 
        this.queueList = queueList; 
        this.doneServingTime = doneServingTime; 
        this.supp = supp;
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
        for(Customer c : this.queueList) {
            queueTimings += c.getServiceTime();
        }
        return queueTimings;
    }

    int getIdentity() {
        return this.identity;
    }

    Supplier<Double> getSupplier() {
        return this.supp; 
    }

    Customer nextCustomer() {
        if (this.queueList.size() != 0) {
            return this.queueList.get(0);
        } else { 
            return new Customer(-1, -1, -1);
        }
    }

}
