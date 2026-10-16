public class LogLevels {
    
    public static String message(String logLine) {
        // Split label and message, then return message.
        String[] logLineComponents = logLine.split("\\[.*\\]:");
        return logLineComponents[1].trim();
    }

    public static String logLevel(String logLine) {
        // Split label and message, then return level.
        String[] logLineComponents = logLine.split(":",2);
        String label = logLineComponents[0].substring(1,logLineComponents[0].length() - 1);
        return label.trim().toLowerCase();
    }

    public static String reformat(String logLine) {
        // Split label and message, normalise label, then return logline in reverse order.
        String[] logLineComponents = logLine.split(":",2);
        String label = logLineComponents[0].replace("[","(").replace("]",")").toLowerCase();
        return logLineComponents[1].trim().concat(" ").concat(label);
    }
}
