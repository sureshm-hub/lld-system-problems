package proj.hobby.lld.logging.strategy.formatter;

import proj.hobby.lld.logging.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
