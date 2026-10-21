public class ExperimentalRemoteControlCar implements RemoteControlCar {

    private int distanceTravelled = 0;
    
    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public void drive() {
        this.distanceTravelled += 20;
    }
}
