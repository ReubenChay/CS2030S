package cs2030.simulator; 
import java.util.List;

class Serve extends Event {

    private final Server server;

    Serve(double arrivalTime, Customer customer, Server server, double serveTime) {
        super(arrivalTime, customer, serveTime); 
        this.server = server;
    }

    Event createNewEvent() {
        double doneTime = super.getEventTime() + super.getCustomerServiceTime(); 
        return new Done(super.getArrivalTime(), super.getCustomer(), this.server,
                doneTime);
    }

    @Override 
    Statistics updateStatistics(Statistics stats) {
        return new Statistics(stats.getNumCustomersServed() + 1,
                stats.getNumCustomersLeft(),
                stats.getTotalWaitTime() + (super.getEventTime() - super.getArrivalTime()));
    }

    @Override 
    int getServerIdentity() {
        return this.server.getIdentity();
    }

    @Override
    Event eventDelayer(double delayTime) {
        return new Serve(super.getArrivalTime(), super.getCustomer(), this.server,
                super.getEventTime() + delayTime);
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
        return true;
    }

    @Override 
    Server updateServer(Server server) {
        List<Customer> newList = server.getQueueList(); 
        newList.remove(super.getCustomer());
        return new Server(this.server.getIdentity(), false, server.getMaxQueue(), newList, 
                super.getEventTime() + super.getCustomerServiceTime() + server.getQueueTimings(), server.getSupplier());
    }

    @Override 
    Server updateSelfCheckout(Server server) {
        List<Customer> newList = server.getQueueList();
        newList.remove(super.getCustomer());
        return new SelfCheckout(server.getIdentity(), false, server.getMaxQueue(), newList, 
                super.getEventTime() + super.getCustomerServiceTime() + server.getQueueTimings(), server.getDefaultIdentity());
    }

    @Override 
    public String toString() {
        if (!this.server.isSelfCheckout()) {
            return String.format("%.3f %d serves by server %d\n", super.getEventTime(), 
                    super.getIdentity(), this.server.getIdentity());
        } else { 
            return String.format("%.3f %d serves by self-check %d\n", super.getEventTime(), 
                    super.getIdentity(), this.server.getIdentity());
        }
    }
}

