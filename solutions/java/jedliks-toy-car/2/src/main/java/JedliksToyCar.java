public class JedliksToyCar {

    private int batteryPercentage = 100;
    private int metresDriven = 0;

    public JedliksToyCar() {
    }
    
    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven ".concat(String.valueOf(metresDriven)).concat(" meters");
    }

    public String batteryDisplay() {
        if (batteryPercentage > 0) {
            return "Battery at ".concat(String.valueOf(batteryPercentage)).concat("%");
        }
        return "Battery empty";
    }

    public void drive() {
        if (batteryPercentage > 0) {
            metresDriven += 20;
            batteryPercentage -= 1;
        }
    }
}
