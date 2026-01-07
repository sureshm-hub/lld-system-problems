package proj.hobby.lld.logging;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.strategy.Appender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private static final LogManager INSTANCE = new LogManager();
    private static final String ROOT_LOGGER = "ROOT";
    private Map<String, Logger> loggers = new ConcurrentHashMap<>();
    private final Logger rootLogger;
    private final AsyncLogProcessor asyncProcessor;

    public LogManager(){
        this.rootLogger = new DefaultLogger(ROOT_LOGGER, null);
        this.loggers.put(ROOT_LOGGER, this.rootLogger);
        this.asyncProcessor = new AsyncLogProcessor();
    }

    public static LogManager getInstance(){
        return  INSTANCE;
    }

    public Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        if(logger == null) {
            logger = createLogger(name);
            loggers.put(name, logger);
        }
        return logger;
    }

    public Logger createLogger(String name) {
        if(ROOT_LOGGER.equals(name)) {
            return  rootLogger;
        }
        int lastDot = name.lastIndexOf('.');
        String parentName = lastDot == -1 ? ROOT_LOGGER : name.substring(0, lastDot);
        Logger parent =  getLogger(parentName);
        return new DefaultLogger(name, parent);
    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public void process(LogMessage logMessage, List<Appender> appenders) {
        asyncProcessor.process(logMessage, appenders);
    }

    public void shutdown() {

    }
}
