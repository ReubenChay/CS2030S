package cs2030.simulator; 

abstract class Event implements Comparable<Event> { 

    private final double arrivalTime;
    private final double eventTime; 
    private final Customer customer;

    Event(double arrivalTime, Customer customer, double eventTime) { 
        this.customer = customer; 
        this.arrivalTime = arrivalTime; 
        this.eventTime = eventTime; 
    }

    double getArrivalTime() {
        return this.arrivalTime;  
    }

    double getEventTime() {
        return this.eventTime; 
    }

    int getIdentity() {
        return this.customer.getIdentity();
    }

    double getCustomerServiceTime() {
        return this.customer.getServiceTime();
    }

    Customer getCustomer() {
        return this.customer;
    }

    abstract Event createNewEvent();

    abstract Server updateServer(Server server); 

    abstract Server updateSelfCheckout(Server server);

    abstract Statistics updateStatistics(Statistics stats);

    abstract int getServerIdentity();

    abstract Event eventDelayer(double delayTime);

    abstract double getRestTime();

    abstract boolean isRest();

    abstract boolean isDone(); 

    abstract boolean isArrive(); 

    abstract boolean isServe(); 

    @Override 
    public int compareTo(Event other) {
        if (this.eventTime < other.getEventTime()) {
            return -1;
        } else if (this.eventTime > other.getEventTime()) {
            return 1;
        } else {
            if (this.getIdentity() < other.getIdentity()) {
                return -1; 
            } else if (this.getIdentity() > other.getIdentity()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

