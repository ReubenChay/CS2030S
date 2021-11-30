import java.util.Collection; 
import java.util.List; 

booking findBestBooking(Request request, Driver[] driverArray) {

    List<Booking> bookingList = new List<>(); 
    for (int i = 0; i < driverArray.length; i++) { 
        bookingList.add(new Booking(request, driverArray[i]));
    }

    Collections.sort(bookingList);
    
    return bookingList.get(0);
}

