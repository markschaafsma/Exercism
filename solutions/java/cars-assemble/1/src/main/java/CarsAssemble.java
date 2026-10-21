public class CarsAssemble {

    private static final int MAX_PROD_RATE_PER_HOUR = 221;
    private static final double SUCCESS_RATE_90_PERCENT = 0.9;
    private static final double SUCCESS_RATE_80_PERCENT = 0.8;
    private static final double SUCCESS_RATE_77_PERCENT = 0.77;
    private static final int MINUTES_PER_HOUR = 60;
    
    public double productionRatePerHour(int speed) throws IllegalArgumentException {
        
        if (speed < 0 || speed > 10) 
            throw new IllegalArgumentException("Invalid speed parameter supplied : " + speed + "; Range allowed is 0-10.");

        if (speed <= 4 ) return speed * MAX_PROD_RATE_PER_HOUR;  
        if (speed <= 8 ) return speed * MAX_PROD_RATE_PER_HOUR * SUCCESS_RATE_90_PERCENT; 
        if (speed == 9 ) return speed * MAX_PROD_RATE_PER_HOUR * SUCCESS_RATE_80_PERCENT; 
        if (speed == 10 ) return speed * MAX_PROD_RATE_PER_HOUR * SUCCESS_RATE_77_PERCENT; 
        return 0.0;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) productionRatePerHour(speed)/MINUTES_PER_HOUR;
    }
}
