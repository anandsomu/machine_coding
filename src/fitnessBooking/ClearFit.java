package fitnessBooking;

import com.sun.tools.javac.util.List;

public class ClearFit {
    public static void main(String[] args) {
        CentreRepository centreRepository = new CentreRepository();
        UserRepository userRepository = new UserRepository();
        ClearFitAdmin clearFitAdmin = new ClearFitAdmin(centreRepository);

        clearFitAdmin.addCentre("Koramangala");
        Slot t1 = new Slot(6, 9);
        Slot t2 = new Slot(18, 21);

        clearFitAdmin.addCentreTimings("Koramangala", List.of(t1,t2) );
        clearFitAdmin.addCentreActivities("Koramangala", List.of("Weights", "Cardio", "Yoga"));

        /**
         * addWorkout(<centerName>, <worjour type>, <start time>, <end time>, <available slots>) addWorkout(“Koramangala”, “Weights”, 6, 7, 100)
         * addWorkout(“Koramangala”, “Cardio”, 7, 8, 150)
         * addWorkout(“Koramangala”, “Yoga”, 8, 9, 200)
         * addWorkout(“Bellandur”, “Weights”, 18, 19, 100) // this should not be allowed because of time addWorkout(“Bellandur”, “Swimming”, 19, 20, 100) // not allowed because of workout type addWorkout(“Bellandur”, “Cardio”, 19, 20, 20)
         * addWorkout(“Bellandur”, “Weights”, 20, 21, 100)
         * addWorkout(“Bellandur”, “Weights”, 21, 22, 100)
         */

        clearFitAdmin.addWorkout("Koramangala", "Cardio", 7,8,100);
        clearFitAdmin.addWorkout("Koramangala", "Yoga", 8,9,200);
        clearFitAdmin.addWorkout("Koramangala", "Cardio", 9,10,100);

        ClearFitUserManager userManager = new ClearFitUserManager(userRepository,centreRepository);
        userManager.registerUser("Vaihbhav");

        /**
         * viewWorkoutAvailability(“Weights”)
         * “Koramangala”, “Weights”, 6, 7, 100
         * “Bellandur”, “Weights”, 20, 21, 100
         * “Bellandur”, “Weights”, 21, 22, 100
         * bookASession
         * bookSession(“Vaibhav”, “Koramangala”, “Weight”, 6, 7)
         */

        userManager.viewWorkoutAvailability("Cardio");
        // incorrect time slot
        userManager.bookASession("Vaihbhav", "Koramangala", "Cardio",6,7);
        // correct time slot
        userManager.bookASession("Vaihbhav", "Koramangala", "Cardio",7,8);

    }
}
