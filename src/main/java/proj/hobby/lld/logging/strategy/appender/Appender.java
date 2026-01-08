package proj.hobby.lld.logging.strategy.appender;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.strategy.formatter.LogFormatter;

public interface Appender {

    void append(LogMessage logMessage);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter logFormatter);
}
