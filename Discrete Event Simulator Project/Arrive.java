package cs2030.simulator; 

import java.util.List; 

class Arrive extends Event {

    private final List<Server> serverList; 
    private static final int DUMMY_SERVER = 0;

    Arrive(double arrivalTime, Customer customer, List<Server> serverList) {
        super(arrivalTime, customer, arrivalTime);
        this.serverList = serverList; 
    }

    // returns index of Server with the shortest queue 
    int greedyCustomerSearch(List<Server> serverList) {
        int index = -1; 
        for (int i = 0; i < serverList.size(); i++) {
            if (!serverList.get(i).checkHasQ()) {
                index = i; 
                break; 
            }
        }

        if (index == -1) {
            return -1; 
        } else { 
            int shortestQueue = serverList.get(index).getCurrentQueue(); 
            for (int i = index; i < serverList.size(); i++) {
                if (!serverList.get(i).checkHasQ() && 
                        (serverList.get(i).getCurrentQueue() < shortestQueue)) {
                    shortestQueue = serverList.get(i).getCurrentQueue();
                    index = i; 
                }
            }
            return index; 
        }
    }


    Event createNewEvent() {
        for (int i = 0; i < serverList.size(); i++) { // loop checks for any idle servers
            if (serverList.get(i).checkIsIdle()) {
                return new Serve(super.getArrivalTime(), super.getCustomer(), serverList.get(i),
                        super.getArrivalTime());
            }
        }

        if (super.getCustomer().isGreedyCustomer()) {
            int serverIndex = greedyCustomerSearch(serverList); 
            if (serverIndex == -1) {
                return new Leave(super.getArrivalTime(), super.getCustomer(), 
                        super.getArrivalTime()); 
            } else {
                double serviceAvailableTime = serverList.get(serverIndex).getDoneServingTime(); 
                return new Wait(super.getArrivalTime(), super.getCustomer(), 
                        serverList.get(serverIndex), serviceAvailableTime); 
            }
        } else { // not greedy customer, do normal search  
            for (int i = 0; i < serverList.size(); i++) { // loop checks for queues at servers
                if (!serverList.get(i).checkHasQ()) {
                    double serviceAvailableTime = serverList.get(i).getDoneServingTime();
                    return new Wait(super.getArrivalTime(), super.getCustomer(), serverList.get(i), 
                            serviceAvailableTime);
                }
            }
        }

        // after both loops, no server free and all have queues 
        return new Leave(super.getArrivalTime(), super.getCustomer(), super.getArrivalTime());
    }


    @Override 
    Statistics updateStatistics(Statistics stats) {
        return stats;
    }

    @Override 
    int getServerIdentity() {
        return DUMMY_SERVER; 
    }


    @Override 
    Server updateServer(Server server) {
        return server;
    }

    @Override 
    Server updateSelfCheckout(Server server) {
        return server;
    }

    @Override 
    Event eventDelayer(double delayTime) {
        return this;
    }

    @Override
    double getRestTime() {
        return 0;
    }

    @Override 
    boolean isRest() {
        return false; 
    }

    @Override 
    boolean isDone() {
        return false; 
    }

    @Override 
    boolean isServe() {
        return false; 
    }

    @Override 
    boolean isArrive() {
        return true; 
    }


    @Override 
    public String toString() {
        return String.format("%.3f %s arrives\n", super.getArrivalTime(),
                super.getCustomer().toString());
    }
}

