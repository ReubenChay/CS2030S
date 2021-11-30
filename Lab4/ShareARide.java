
class ShareARide extends CabService {
    
    private static final int DISTANCE_FARE = 50; 
    private static final int SURCHARGE = 500; 

    ShareARide() { }

    int computeFare(Request request) {
        int fare = 0; 
        fare += DISTANCE_FARE * request.getDistance();
        if (request.getTime() >= 600 && request.getTime() <= 900) {
            fare += SURCHARGE;
        }
        fare /= request.getNumPassenger();
        return fare; 
    }

    @Override 
    public String toString() {
        return "ShareARide";
    }
}
