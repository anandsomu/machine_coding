package fitnessBooking;
import java.util.*;
public class User {
    private String name;
    List<Booking> bookings;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        if(this.bookings==null){
            this.bookings = new ArrayList<>();
        }
        bookings.add(booking);
    }

    public void removeBookings(Centre centre, int startTime, int endTime) {
        Booking booking;
        this.getBookings().forEach(b ->{
            if(b.getCentre().equals(centre) && b.getSlot().getStartTime()==startTime && b.getSlot().getEndTime()==endTime){

            }
        });
    }
}
