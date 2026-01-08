package proj.hobby.lld.logging;

import proj.hobby.lld.logging.enums.LogLevel;
import proj.hobby.lld.logging.strategy.appender.ConsoleAppender;

public class LoggingFrameworkDemo {

    public static void main(String[] args) {
        // Initialize Configuration: Root Logger
        LogManager logManager = LogManager.getInstance();
        Logger rootLogger = logManager.getRootLogger();
        rootLogger.setLevel(LogLevel.INFO);

        // Add Console Appender
        rootLogger.addAppender(new ConsoleAppender());

        // Application Logger
        Logger logger  = logManager.getLogger(LoggingFrameworkDemo.class.getName());
        logger.info("Info Message");
        logger.debug("debug Message");
        logger.warn("warn message");

        // Dynamic Configuration Changes
        rootLogger.setLevel(LogLevel.WARN);
        logger.info("info message 2");
        logger.warn("warn message 2");

        // Hierarchy & Additivity
        Logger custom  = logManager.getLogger("proj.hobby.lld.logging.Custom");
        custom.setLevel(LogLevel.DEBUG);
        custom.info("Custom Info Message");
        custom.debug("Custom Debug Message");
        custom.warn("Custom Warn message");

        try {
            Thread.sleep(1000);
            // Shutdown LogManager
            logManager.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
