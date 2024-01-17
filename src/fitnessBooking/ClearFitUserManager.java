package fitnessBooking;

import java.util.*;

public class ClearFitUserManager {
    private Map<String, User> users;
    private Map<String,Centre> centres;

    public ClearFitUserManager(UserRepository userRepository, CentreRepository centreRepository) {
        this.users = userRepository.getUsers();
        this.centres = centreRepository.getCentres();
    }

    public void registerUser(String userName){
        users.put(userName,new User(userName));
    }
    public void viewWorkoutAvailability(String workoutType){
        this.centres.forEach((centreName,centre)->{
            if(centre.getActivities().contains(workoutType)){
                centre.getWorkouts().stream().filter(w->w.getWorkoutType().equals(workoutType))
                        .forEach(w->{
                            w.getSlots().forEach(slot -> {
                                System.out.println(centreName + ","+workoutType
                                        +","+slot.getStartTime()+","+ slot.getEndTime()+","+slot.getSeats());
                            });
                        });
            }
        });
    }
    public void bookASession(String userName, String centerName,String workoutType, int startTime, int endTime){
        if(!this.users.containsKey(userName)){
            System.out.println("User not registered");
            return;
        }
        if(!this.centres.containsKey(centerName)){
            System.out.println("Incorrect centre name");
            return;
        }
        if(!this.centres.get(centerName).getActivities().contains(workoutType)){
            System.out.println("Incorrect workout type");
            return;
        }

        User user = this.users.get(userName);
        Centre centre = this.centres.get(centerName);
        final boolean[] booked = new boolean[1];
        centre.getWorkouts().stream().filter(w->w.getWorkoutType().equals(workoutType))
                .forEach(w->{
                    w.getSlots().forEach(slot -> {
                        if(slot.getStartTime()==startTime && slot.getEndTime()==endTime){
                            user.addBooking(new Booking(centre, slot));
                            int updatedSeats = slot.getSeats()-1;
                            slot.setSeats(updatedSeats);
                            booked[0] =true;
                            System.out.println("Booked");
                        }
                    });
                    if(!booked[0]){
                        System.out.println("Invalid Timing");
                    }
                });
    }

    public void cancelSession(String userName, String centerName,String workoutType, int startTime, int endTime){
        if(!this.users.containsKey(userName)){
            System.out.println("User not registered");
            return;
        }
        if(!this.centres.containsKey(centerName)){
            System.out.println("Incorrect centre name");
            return;
        }
        if(!this.centres.get(centerName).getActivities().contains(workoutType)){
            System.out.println("Incorrect workout type");
            return;
        }

        User user = this.users.get(userName);
        Centre centre = this.centres.get(centerName);
        centre.getWorkouts().stream().filter(w->w.getWorkoutType().equals(workoutType))
                .forEach(w->{
                    w.getSlots().forEach(slot -> {
                        if(slot.getStartTime()==startTime && slot.getEndTime()==endTime){
                            int updatedSeats = slot.getSeats()+1;
                            slot.setSeats(updatedSeats);
                            System.out.println("Booking Canceled");
                        }
                    });
                });


    }
}
