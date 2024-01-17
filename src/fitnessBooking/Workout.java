package fitnessBooking;
import java.util.*;
public class Workout {
    private String workoutType;
    private List<Slot> slots;

    public Workout(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
