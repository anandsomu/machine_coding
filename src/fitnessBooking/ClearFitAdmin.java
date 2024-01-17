package fitnessBooking;
import java.util.*;

import static java.util.Objects.nonNull;

public class ClearFitAdmin {

    private Map<String,Centre> centres;

    public ClearFitAdmin(CentreRepository centreRepository) {
        this.centres = centreRepository.getCentres();
    }

    public void addCentre(String name){
        Centre centre = new Centre(name);
        this.centres.put(name, centre);
        System.out.println("Center Added"+name);
    }

    public void addCentreTimings(String name, List<Slot> timingSlots){
        Centre centre = centres.get(name);
        centre.setSlots(timingSlots);
        System.out.println("Timing Added for center"+name);
    }

    public void addCentreActivities(String name, List<String> activities){
        Centre centre = centres.get(name);
        centre.setActivities(activities);
        System.out.println("Activities added for centre"+name);
    }

    public void addWorkout(String centreName, String workoutType, int startTime, int endTime, int availableSlots){
        Centre center = centres.get(centreName);
        if(center == null){
            System.out.println("Invalid centre name"+ centreName);
            return;
        }
        if(!center.getActivities().contains(workoutType)){
            System.out.println("Invalid workout type"+ workoutType);
            return;
        }

        if(!this.validateCenterTimings(center,startTime, endTime)){
            System.out.println("Invalid workout timings "+ workoutType);
            return;
        }
        Slot slot = new Slot(startTime,endTime);
        slot.setSeats(availableSlots);

        if(nonNull(center.getWorkouts()) && !center.getWorkouts().isEmpty()) {
            Optional<Workout> workout = center.getWorkouts().stream().filter(w -> w.getWorkoutType().equals(workoutType)).findFirst();
            if(workout.isPresent()) {
                workout.get().getSlots().add(slot);
            }else{
                addNewWorkout(center,workoutType, slot);
            }
        }else{
            addNewWorkout(center,workoutType, slot);
        }
        System.out.println("Added workout timing for" + workoutType);
    }
    private void addNewWorkout(Centre center, String workoutType, Slot slot) {
        Workout newWorkout = new Workout(workoutType);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot);
        newWorkout.setSlots(slots);
        center.addWorkout(newWorkout);
    }

    private boolean validateCenterTimings(Centre centre, int startTime, int endTime){
        //todo
        return true;
    }

    public Map<String, Centre> getCentres() {
        return centres;
    }
}
