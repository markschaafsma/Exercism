public class ExperimentalRemoteControlCar implements RemoteControlCar {

    private static final int DISTANCE_PER_DRIVE = 20;    
    private int distanceTravelled = 0;

    @Override
    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    @Override
    public void drive() {
        this.distanceTravelled += DISTANCE_PER_DRIVE;;
    }
}
