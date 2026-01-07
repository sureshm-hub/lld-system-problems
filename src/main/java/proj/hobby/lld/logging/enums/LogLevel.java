package proj.hobby.lld.logging.enums;

public enum LogLevel {

    DEBUG(1), INFO(2), WARN(3), ERROR(4);

    private int level;

    LogLevel(int level){
        this.level = level;
    }

    public boolean isGreaterOrEqual(LogLevel other) {
        return this.level >= other.level;
    }
}
