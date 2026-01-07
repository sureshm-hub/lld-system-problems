package proj.hobby.lld.logging.strategy;

import proj.hobby.lld.logging.entities.LogMessage;

public class ConsoleAppender implements  Appender{
    @Override
    public void append(LogMessage logMessage) {

    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return null;
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {

    }
}
