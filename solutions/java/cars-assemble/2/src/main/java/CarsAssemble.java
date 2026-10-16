import java.util.Map;

public class CarsAssemble {

    private static final int MAX_PROD_RATE_PER_HOUR = 221;
    private static final int MINUTES_PER_HOUR = 60;
    private static final Map<Integer, Double> SUCCESS_RATE = Map.ofEntries (
        Map.entry(0, 1.0),
        Map.entry(1, 1.0),
        Map.entry(2, 1.0),
        Map.entry(3, 1.0),
        Map.entry(4, 1.0),
        Map.entry(5, 0.9),
        Map.entry(6, 0.9),
        Map.entry(7, 0.9),
        Map.entry(8, 0.9),
        Map.entry(9, 0.8),
        Map.entry(10,0.77)
    );
    
    public double productionRatePerHour(int speed) throws IllegalArgumentException {

        if (speed < 0 || speed > 10) 
            throw new IllegalArgumentException("Invalid speed parameter supplied : " + speed + "; Range allowed is 0-10.");

        return speed * MAX_PROD_RATE_PER_HOUR * SUCCESS_RATE.get(speed);
    }

    public int workingItemsPerMinute(int speed) {
        return (int) productionRatePerHour(speed) / MINUTES_PER_HOUR;
    }
}
