package cs2030.simulator; 

import java.util.List; 

class SelfCheckout extends Server {

    private final int defaultIdentity; 

    SelfCheckout(int identity, boolean isIdle, int maxQueue, List<Customer> queueList,
            double doneServingTime, int defaultIdentity) {
        super(identity, isIdle, maxQueue, queueList, doneServingTime, 0, () -> 0.0, () -> 0.0);
        this.defaultIdentity = defaultIdentity; 
    }

    int getDefaultIdentity() {
        return this.defaultIdentity;
    }

    boolean isSelfCheckout() {
        return true;
    }


}

