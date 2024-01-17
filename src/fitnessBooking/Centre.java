package fitnessBooking;
import java.util.*;
public class Centre {
    private String name;
    private List<Slot>  slots;
    private List<String> activities;

    private List<Workout> workouts;

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public Centre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots= slots;
    }

    public void addSlot(Slot slot) {
        if(this.slots.isEmpty()){
            this.slots = new ArrayList<>();
        }
        slots.add(slot);
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}
