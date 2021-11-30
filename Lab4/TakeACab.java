
class TakeACab extends CabService {

    private static final int DISTANCE_FARE = 33; 
    private static final int BOOKING_FEE = 200; 

    TakeACab() { }


    int computeFare(Request request) {
        int fare = 0; 
        fare += BOOKING_FEE; 
        fare += DISTANCE_FARE * request.getDistance();
        return fare; 
    }

    @Override 
    public String toString() {
        return "TakeACab";
    }
}
