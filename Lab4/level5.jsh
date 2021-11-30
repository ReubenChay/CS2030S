import java.util.Collection; 
import java.util.List; 

Booking findBestBooking(Request request, Driver[] driverArray) {

    List<Booking> bookingList = new ArrayList<>(); 
    for (int i = 0; i < driverArray.length; i++) { 
        bookingList.add(new Booking(driverArray[i], request));
    }

// Collections.sort(bookingList);
   bookingList.sort(null);
    
    return bookingList.get(0);
}

/**
Booking findBestBooking(Request request, Driver[] driverArray) {

    Booking bestBooking = new Booking(driverArray[0], request);
    
    for (int i = 1; i < driverArray.length; i++) {
        if (bestBooking.compareTo(new Booking(driverArray[i], request)) == -1) {
            continue;
        }
        if (bestBooking.compareTo(new Booking(driverArray[i], request)) == 1) {
            bestBooking = new Booking(driverArray[i], request);
        }
    }

    return bestBooking;

}
*/
