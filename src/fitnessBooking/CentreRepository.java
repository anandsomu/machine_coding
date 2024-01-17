package fitnessBooking;

import java.util.HashMap;
import java.util.Map;

public class CentreRepository {
    private Map<String,Centre> centres = new HashMap<String,Centre>();

    public Map<String, Centre> getCentres() {
        return centres;
    }

    public void setCentres(Map<String, Centre> centres) {
        this.centres = centres;
    }
}
