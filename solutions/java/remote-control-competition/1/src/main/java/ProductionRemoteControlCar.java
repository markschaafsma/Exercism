public class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {

    private int distanceTravelled = 0;
    private int numberOfVictories = 0;

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public void drive() {
        this.distanceTravelled += 10;
    }

    @Override
    public int compareTo(ProductionRemoteControlCar that) {
        if (that == null) throw new NullPointerException();
        return Integer.compare(this.getNumberOfVictories(), that.getNumberOfVictories());
    }
}
