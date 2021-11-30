package cs2030.simulator;

class RestDone extends Event {

    private final Server server;

    RestDone(double arrivalTime, Customer customer, Server server, double endOfRest) {
        super(arrivalTime, customer, endOfRest);
        this.server = server;
    }

    @Override 
    Event createNewEvent() {
        return this; 
    }

    @Override 
    Server updateServer(Server server) {
        return new Server(server.getIdentity(), true, server.getMaxQueue(), server.getQueueList(), 
                server.getDoneServingTime(), server.getSupplier());
    }

    @Override
    Server updateSelfCheckout(Server server) {
        return new SelfCheckout(server.getIdentity(), true, server.getMaxQueue(), server.getQueueList(),
                server.getDoneServingTime(), server.getDefaultIdentity());
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
    boolean isRest() {
        return false;
    }

    @Override
    double getRestTime() {
        return 0;
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
        return "";
        // return String.format("%.3f server %d done resting\n", super.getEventTime(), this.getServerIdentity());
    }

}






