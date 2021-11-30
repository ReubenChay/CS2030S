package cs2030.simulator;

class Statistics { 

    private final int numCustomersServed; 
    private final int numCustomersLeft; 
    private final double totalWaitTime; 

    Statistics() {
        this.numCustomersServed = 0;
        this.numCustomersLeft = 0; 
        this.totalWaitTime = 0.0;
    }

    Statistics(int numCustomersServed, int numCustomersLeft, double totalWaitTime) {
        this.numCustomersServed = numCustomersServed; 
        this.numCustomersLeft = numCustomersLeft; 
        this.totalWaitTime = totalWaitTime; 
    }

    int getNumCustomersServed() {
        return this.numCustomersServed;
    }

    int getNumCustomersLeft() {
        return this.numCustomersLeft; 
    }

    double getTotalWaitTime() {
        return this.totalWaitTime;
    }

}
