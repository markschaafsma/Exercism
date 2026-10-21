public enum LogLevel {
    UNKNOWN (0, "Unknown"),
    TRACE   (1, "Trace"),
    DEBUG   (2, "Debug"),
    INFO    (4, "Info"),
    WARNING (5, "Warning"),
    ERROR   (6, "Error"),
    FATAL   (42, "Fatal");
    
    private final int logLevelNumber;
    private final String logDescription;

    LogLevel(int logLevelNumber, String logDescription) {
        this.logLevelNumber = logLevelNumber;
        this.logDescription = logDescription;
    }
    
    public int getLogLevelNumber() {
        return this.logLevelNumber;
    }

    public String getLogDescription() {
        return this.logDescription;        
    }
}
