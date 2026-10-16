public class LogLine {

    private String logLine;

    public LogLine(String logLine) {
        this.logLine = logLine;
    }

    public LogLevel getLogLevel() {
        String logLineLevel = this.logLine.substring(1,4);
        return 
            switch (logLineLevel) {
                    case "TRC" -> LogLevel.TRACE;
                    case "DBG" -> LogLevel.DEBUG;
                    case "INF" -> LogLevel.INFO;
                    case "WRN" -> LogLevel.WARNING;
                    case "ERR" -> LogLevel.ERROR;
                    case "FTL" -> LogLevel.FATAL;
                    default    -> LogLevel.UNKNOWN;
            };
    }

    public String getOutputForShortLog() {
        String[] logComponents = this.logLine.split(":");
        return getLogLevel().getLogLevelNumber() + ":" + logComponents[1].trim();
    }
}
