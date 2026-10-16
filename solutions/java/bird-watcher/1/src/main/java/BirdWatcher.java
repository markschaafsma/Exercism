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
        for (int birds : this.birdsPerDay) {
            if (birds == 0) return true; 
        }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int birdCount = 0;
        for (int i = 0; i < numberOfDays && i < this.birdsPerDay.length; i++) {
            birdCount += this.birdsPerDay[i];
        }
        return birdCount;
    }

    public int getBusyDays() {
        int busyDays = 0;
        for (int birds : this.birdsPerDay) {
            if (birds >= 5) busyDays++;
        }
        return busyDays;
    }
}
