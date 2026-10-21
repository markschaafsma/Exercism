import java.util.Arrays;

class BirdWatcher {
    
    private final int[] birdsPerDay;
    private static int[] lastWeeksCount = {0,2,5,3,7,8,4};

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public static int[] getLastWeek() {
        return BirdWatcher.lastWeeksCount;                
    }

    public int getToday() {
        return this.birdsPerDay[6];
    }

    public void incrementTodaysCount() {
        this.birdsPerDay[6]++; 
    }

    public boolean hasDayWithoutBirds() {
        return Arrays.stream(birdsPerDay)
                .anyMatch(birds -> birds == 0);
    }

    public int getCountForFirstDays(int numberOfDays) {
        numberOfDays = numberOfDays < birdsPerDay.length ? numberOfDays : birdsPerDay.length; 
        return Arrays.stream(birdsPerDay, 0, numberOfDays)
                .sum();
    }

    public int getBusyDays() {
        return (int) Arrays.stream(birdsPerDay)
                .filter(birds -> birds >= 5)
                .count();
    }
}
