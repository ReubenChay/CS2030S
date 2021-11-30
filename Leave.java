package cs2030.simulator; 

class Leave extends Event {

    private static final int DUMMY_SERVER = 0;


    Leave(double time, Customer customer, double leaveTime) {
        super(time, customer, leaveTime); 
    }

    Event createNewEvent() {
        return this;
    }

    @Override 
    Statistics updateStatistics(Statistics stats) {
        return new Statistics(stats.getNumCustomersServed(), stats.getNumCustomersLeft() + 1,
                stats.getTotalWaitTime());
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
    double getRestTime() {
        return 0;
    }

    @Override
    boolean isRest() {
        return false;
    }

    @Override 
    Event eventDelayer(double delayTime) {
        return this;
    }

    @Override 
    int getServerIdentity() {
        return DUMMY_SERVER; 
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
        return String.format("%.3f %d leaves\n", super.getEventTime(), 
                super.getIdentity());
    }
}


