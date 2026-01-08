package proj.hobby.lld.logging.strategy.appender;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.strategy.formatter.LogFormatter;

public class FileAppender implements Appender{
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
