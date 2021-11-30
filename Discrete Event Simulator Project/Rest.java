package cs2030.simulator; 

class Rest extends Event {

    private final double restTime;
    private final Server server;

    Rest(double arrivalTime, Customer customer, double restTime, Server server, 
            double startOfRest) {
        super(arrivalTime, customer, startOfRest); 
        this.restTime = restTime; 
        this.server = server;
    }

    @Override
    Event createNewEvent() {
        return new RestDone(super.getArrivalTime(), this.getCustomer(), 
                this.server, super.getEventTime() + this.restTime);
    }

    @Override 
    Server updateServer(Server server) {
        return new Server(server.getIdentity(), false, server.getMaxQueue(), server.getQueueList(),
                server.getDoneServingTime() + this.restTime, server.getRestingThreshold(), 
                server.getSupplier(), server.getRestProbSupplier());
    }

    @Override
    Server updateSelfCheckout(Server server) {
        return new SelfCheckout(server.getIdentity(), false, 
                server.getMaxQueue(), server.getQueueList(),
                server.getDoneServingTime() + this.restTime, server.getDefaultIdentity());
    }


    @Override 
    Statistics updateStatistics(Statistics stats) {
        return stats; 
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
        return this.restTime; 
    }

    @Override
    boolean isRest() {
        return true;
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
        return false;
    }



    @Override 
    public String toString() {
        return "";
    }

}


