package proj.hobby.lld.logging;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.enums.LogLevel;
import proj.hobby.lld.logging.strategy.appender.Appender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultLogger implements Logger{

    private final Logger parent;
    private final String name;
    private LogLevel level;
    private List<Appender> appenders;
    private boolean additivity = true;

    public DefaultLogger(String name, Logger parent) {
        this.name = name;
        this.parent = parent;
        this.appenders = new CopyOnWriteArrayList<>();
    }

    public List<Appender> getAppenders() {
        return appenders;
    }

    public void addAppender(Appender appender) {
        getAppenders().add(appender);
    }


    @Override
    public Logger getParent() {
        return parent;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

    public LogLevel getEffectiveLogLevel(){
        Logger current = this;
        while(current != null && current.getLevel() == null) {
            current = current.getParent();
        }
        return current == null ? LogLevel.INFO : current.getLevel();
    }

    public void log(LogLevel level, String message) {
        if(level.isGreaterOrEqual(getEffectiveLogLevel())) {
            LogMessage logMessage = new LogMessage(level, name, message);
            callAppenders(logMessage);
        }
    }

    public void callAppenders(LogMessage logMessage) {
        if(!appenders.isEmpty()) {
            LogManager.getInstance().process(logMessage, appenders);
        }
        if(additivity & parent != null) {
            parent.callAppenders(logMessage);
        }
    }

    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
