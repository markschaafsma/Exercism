class NeedForSpeed {

    private int metresDriven;
    private int batteryCharge;
    private int speed;
    private int batteryDrain;

    private static final int NITRO_SPEED = 50;
    private static final int NITRO_BATTERY_DRAIN = 4; 

    public NeedForSpeed(int speed, int batteryDrain) {
        this.metresDriven = 0;
        this.batteryCharge = 100; 
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getBatteryDrain() {
        return this.batteryDrain;
    }

    public boolean batteryDrained() {
        return this.batteryCharge < this.batteryDrain;
    }

    public int distanceDriven() {
        return this.metresDriven;
    }

    public void drive() {
        if (this.batteryCharge >= this.batteryDrain) {
            this.metresDriven += speed;
            this.batteryCharge -= batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(NITRO_SPEED, NITRO_BATTERY_DRAIN);
    }
}

class RaceTrack {

    private int distance;
    
    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        int carRange = car.getSpeed() * 100/car.getBatteryDrain();
        return carRange >= this.distance;
    }
}
