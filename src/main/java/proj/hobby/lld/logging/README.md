# Logging Framework

---

## Problem Statement

Design & Implement a logging framework that can be used by applications to log  messages at various levels (INFO, DEBUG, WARN, ERROR, etc.). 
Support multiple output destinations (file, console etc.), allow for custom formating of log messages

---

## Requirements

- Log Messages at DEBUG, INFO, WARN, ERROR
- Log to destinations console, file etc.
- Message Formatting
- Configuration
- Thread Safety

---

## Entities

- LogManager
- Logger
- LogLevel
- LogFormatter
- DefaultLogFormatter
- LogAppender
- ConsoleAppender
- FileAppender
- LoggerConfig
- LogMessage


---

## Design Notes

### Design Patterns

- **Singleton** LogManager
- **Strategy** LogFormatter, FileAppender

### Do's
- Logger Interface
- DefaultLogger.getEffectiveLogLevel
- DefaultLogger.appenders - CopyOnWriteArrayList
- LogLevel.isGraterOrEqual()
- LogManager.loggers - ConcurrentHashMap
- LogManager.getLogger(), createLogger()
- AsyncProcessor.process
- LogFormatter & Appender