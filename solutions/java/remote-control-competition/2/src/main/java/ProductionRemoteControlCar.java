public class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {

    private static final int DISTANCE_PER_DRIVE = 10; 

    private int distanceTravelled = 0;
    private int numberOfVictories = 0;

    @Override
    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    @Override
    public void drive() {
        this.distanceTravelled += DISTANCE_PER_DRIVE;
    }

    @Override
    public int compareTo(ProductionRemoteControlCar that) {
        if (that == null) throw new NullPointerException();
        return Integer.compare(this.getNumberOfVictories(), that.getNumberOfVictories());
    }
}
