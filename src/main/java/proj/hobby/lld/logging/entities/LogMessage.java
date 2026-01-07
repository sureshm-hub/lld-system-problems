package proj.hobby.lld.logging.entities;

import proj.hobby.lld.logging.enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {
    private final LocalDateTime logTimeStamp;
    private final LogLevel level;
    private final String message;
    private final String loggerName;
    private final String threadName;

    public LogMessage(LogLevel level, String message, String loggerName) {
        this.logTimeStamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
        this.loggerName = loggerName;
        this.threadName = Thread.currentThread().getName();
    }

    // Getters
    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getThreadName() {
        return threadName;
    }

    public LocalDateTime getLogTimeStamp() {
        return logTimeStamp;
    }
}
