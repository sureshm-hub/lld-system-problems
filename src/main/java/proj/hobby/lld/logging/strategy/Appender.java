package proj.hobby.lld.logging.strategy;

import proj.hobby.lld.logging.entities.LogMessage;

public interface Appender {

    void append(LogMessage logMessage);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter logFormatter);
}
