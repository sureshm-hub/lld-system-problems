package proj.hobby.lld.logging.strategy;

import proj.hobby.lld.logging.entities.LogMessage;

public class SimpleLogFormatter implements  LogFormatter{
    @Override
    public String format(LogMessage logMessage) {
        return "";
    }
}
