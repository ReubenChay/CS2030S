
class Request { 
    private final int distance; 
    private final int numPassenger; 
    private final int time; 

    Request(int distance, int numPassenger, int time) {
        this.distance = distance; 
        this.numPassenger = numPassenger; 
        this.time = time; 
    }

    int getDistance() {
        return this.distance;
    }

    int getNumPassenger() {
        return this.numPassenger; 
    }

    int getTime() {
        return this.time;
    }

    @Override 
    public String toString() {
        return distance + "km for " + numPassenger + "pax @ " + 
            time + "hrs";
    }
}
