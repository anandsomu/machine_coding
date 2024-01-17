package fitnessBooking;

public class Booking {
    private Centre centre;
    private Slot slot;

    public Booking(Centre centre, Slot slot) {
        this.centre = centre;
        this.slot = slot;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
