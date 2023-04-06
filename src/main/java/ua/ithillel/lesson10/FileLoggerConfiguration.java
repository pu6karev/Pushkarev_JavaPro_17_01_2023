package ua.ithillel.lesson10;

public class FileLoggerConfiguration {
    private final String path;
    private final LoggingLevel eLevel;

    private final long maxFileSize;


    public FileLoggerConfiguration(String path, LoggingLevel eLevel, long maxFileSize) {
        this.path = path;
        this.eLevel = eLevel;
        this.maxFileSize = maxFileSize;
    }

    public String getPath() {
        return path;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public LoggingLevel getLogLevel() {
        return eLevel;
    }

}