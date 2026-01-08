package proj.hobby.lld.logging.strategy.formatter;

import proj.hobby.lld.logging.entities.LogMessage;

import java.time.format.DateTimeFormatter;

public class SimpleLogFormatter implements  LogFormatter{

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public String format(LogMessage logMessage) {
        return String.format("%s [%s] %s - %s: %s\n", DATE_TIME_FORMATTER.format(logMessage.getLogTimeStamp()),
                logMessage.getThreadName(),
                logMessage.getLevel(),
                logMessage.getLoggerName(),
                logMessage.getMessage());
    }
}
