package cs2030.simulator; 

class Done extends Event {

    private final Server server;

    Done(double arrivalTime, Customer customer, Server server, double doneTime) {
        super(arrivalTime, customer, doneTime);
        this.server = server;
    }

    Event createNewEvent() {
        return new Rest(super.getArrivalTime(), super.getCustomer(), 
                this.server.getSupplier().get(), this.server,
                super.getEventTime());
    }

    @Override 
    Statistics updateStatistics(Statistics stats) {
        return stats; 
    }

    @Override 
    int getServerIdentity() {
        return this.server.getIdentity();
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
        return true;
    }

    @Override
    boolean isServe() {
        return false;
    }

    @Override 
    Server updateServer(Server server) {
        if (server.getCurrentQueue() >= 1) {
            return new Server(this.server.getIdentity(), false, server.getMaxQueue(),
                    server.getQueueList(), server.getDoneServingTime() , server.getSupplier());
        } else { // no queue 
            return new Server(this.server.getIdentity(), true, server.getMaxQueue(), 
                    server.getQueueList(), server.getDoneServingTime(), server.getSupplier());
        }
    }
    @Override
    Server updateSelfCheckout(Server server) {
        if (server.getCurrentQueue() >= 1) {
            return new SelfCheckout(this.server.getIdentity(), false, server.getMaxQueue(),
                    server.getQueueList(), server.getDoneServingTime() , server.getDefaultIdentity());
        } else { // no queue
            return new SelfCheckout(this.server.getIdentity(), true, server.getMaxQueue(),
                    server.getQueueList(), server.getDoneServingTime(), server.getDefaultIdentity());
        }
    }



    @Override 
    public String toString() {
        if (!this.server.isSelfCheckout()) {
            return String.format("%.3f %d done serving by server %d\n", super.getEventTime(),
                    super.getIdentity(), this.server.getIdentity());
        } else {
            return String.format("%.3f %d done serving by self-check %d\n", super.getEventTime(), 
                    super.getIdentity(), this.server.getIdentity());
        }
    }
}



