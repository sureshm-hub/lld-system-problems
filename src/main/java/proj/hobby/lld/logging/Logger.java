package proj.hobby.lld.logging;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.enums.LogLevel;
import proj.hobby.lld.logging.strategy.appender.Appender;

public interface Logger {

    public void setLevel(LogLevel level);
    public void addAppender(Appender appender);

    void callAppenders(LogMessage logMessage);
    Logger getParent();
    LogLevel getLevel();

    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
}
