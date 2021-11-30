package cs2030.simulator; 
import java.util.List;

class Wait extends Event {

    private final Server server;
    private final double serverAvailableTime;

    Wait(double arrivalTime, Customer customer, Server server, double serverAvailableTime) {
        super(arrivalTime, customer, arrivalTime); 
        this.server = server; 
        this.serverAvailableTime = serverAvailableTime; 
    }

    Event createNewEvent() {
        return new Serve(super.getArrivalTime(),super.getCustomer(), 
                this.server, this.serverAvailableTime);
    }


    @Override
    Statistics updateStatistics(Statistics stats) {
        return stats; 
    }

    @Override 
    Server updateServer(Server server) {
        List<Customer> newList = server.getQueueList(); 
        newList.add(super.getCustomer());
        return new Server(this.server.getIdentity(), false, server.getMaxQueue(), newList, 
                this.serverAvailableTime + super.getCustomerServiceTime(), server.getSupplier());
    }

    @Override 
    Server updateSelfCheckout(Server server) {
        List<Customer> newList = server.getQueueList();
        newList.add(super.getCustomer());
        return new SelfCheckout(server.getIdentity(), false, server.getMaxQueue(), newList,
                this.serverAvailableTime + super.getCustomerServiceTime(), server.getDefaultIdentity());
    }

    @Override 
    Event eventDelayer(double delayTime) {
        return this; 
    }


    @Override 
    int getServerIdentity() {
        return this.server.getIdentity();
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
    public String toString() {
        if (!this.server.isSelfCheckout()) {
            return String.format("%.3f %d waits at server %d\n", super.getEventTime(), 
                    super.getIdentity(), this.server.getIdentity());
        } else {
            return String.format("%.3f %d waits at self-check %d\n", super.getEventTime(), 
                    super.getIdentity(), this.server.getDefaultIdentity());
        }
    }
}


