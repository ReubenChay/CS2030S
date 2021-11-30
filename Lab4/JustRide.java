
class JustRide extends CabService {

    private static final int DISTANCE_FARE = 22; 
    private static final int SURCHARGE = 500; 

    JustRide() { } 

     int computeFare(Request request) {
        int fare = 0;
        fare += DISTANCE_FARE * request.getDistance(); 
        if (request.getTime() >= 600 && request.getTime() <= 900) {
            fare += SURCHARGE; 
        }
        return fare; 
    }

    @Override 
    public String toString() {
        return "JustRide";
    }
}

