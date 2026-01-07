package proj.hobby.lld.logging.strategy;

import proj.hobby.lld.logging.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
