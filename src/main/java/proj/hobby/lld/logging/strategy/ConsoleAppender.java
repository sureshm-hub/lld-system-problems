package proj.hobby.lld.logging.strategy;

import proj.hobby.lld.logging.entities.LogMessage;

public class ConsoleAppender implements  Appender{

    private LogFormatter formatter;

    public ConsoleAppender() {
        this.formatter = new SimpleLogFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return this.formatter;
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.formatter = logFormatter;
    }
}
