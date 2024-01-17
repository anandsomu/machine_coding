package fitnessBooking;

import java.util.*;

public class ClearFitUserManager {
    private Map<String, User> users;
    private Map<String,Centre> centres;

    public ClearFitUserManager() {
        UserRepository userRepository = new UserRepository();
        this.users = userRepository.getUsers();

        CentreRepository centreRepository = new CentreRepository();
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

        centre.getWorkouts().stream().filter(w->w.getWorkoutType().equals(workoutType))
                .forEach(w->{
                    w.getSlots().forEach(slot -> {
                        if(slot.getStartTime()==startTime && slot.getEndTime()==endTime){
                            user.bookings.add(new Booking(centre, slot));
                            int updatedSeats = slot.getSeats()-1;
                            slot.setSeats(updatedSeats);
                            System.out.println("Booked");
                        }
                    });
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
