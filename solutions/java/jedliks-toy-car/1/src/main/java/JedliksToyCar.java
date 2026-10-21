public class JedliksToyCar {

    private int batteryCharge;
    private int metresDriven;

    public JedliksToyCar() {
        batteryCharge = 100;
        metresDriven = 0;
    }
    
    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven ".concat(String.valueOf(metresDriven)).concat(" meters");
    }

    public String batteryDisplay() {
        if (batteryCharge > 0) {
            return "Battery at ".concat(String.valueOf(batteryCharge)).concat("%");
        }
        return "Battery empty";
    }

    public void drive() {
        if (batteryCharge > 0) {
            metresDriven += 20;
            batteryCharge -= 1;
        }
    }
}
