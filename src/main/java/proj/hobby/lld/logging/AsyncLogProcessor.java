package proj.hobby.lld.logging;

import proj.hobby.lld.logging.entities.LogMessage;
import proj.hobby.lld.logging.strategy.Appender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncLogProcessor {

    private final ExecutorService executorService;

    public AsyncLogProcessor(){
        this.executorService = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = new Thread(runnable, "AsyncLogProcessor");
            thread.setDaemon(true);
            return thread;
        });
    }

    public void process(LogMessage logMessage, List<Appender> appenders) {
        executorService.submit(() -> {
            for(Appender appender : appenders) {
                appender.append(logMessage);
            }
        });
    }

    public void stop() {

    }
}
