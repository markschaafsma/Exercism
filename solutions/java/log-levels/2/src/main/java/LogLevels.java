public class LogLevels {
    
    public static String message(String logLine) {
        // Split logLine to find message based on a robust regex expression for logLevel.
        String[] logLineComponents = logLine.split("\\[.*\\]:");
        return logLineComponents[1].trim();
    }

    public static String logLevel(String logLine) {
        // Split logLevel and message based on the simple delimiter ":" tested just once
        String[] logLineComponents = logLine.split(":",2);
        String logLevel = logLineComponents[0].substring(1,logLineComponents[0].length() - 1);
        return logLevel.trim().toLowerCase();
    }

    public static String reformat(String logLine) {
        return message(logLine).concat(" (").concat(logLevel(logLine)).concat(")");
    }
}
