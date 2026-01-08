package proj.hobby.lld.trafficController;

import proj.hobby.lld.trafficController.observer.CentralMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficControlSystem {

    private static final TrafficControlSystem INSTANCE = new TrafficControlSystem();
    private final List<Intersection> intersections = new ArrayList<>();
    private ExecutorService executor;

    public static TrafficControlSystem getInstance() {
        return INSTANCE;
    }

    public void addIntersection(int interSectionId, long greenDuration, long yellowDuration) {
        Intersection intersection = new Intersection.Builder(interSectionId)
                .withDuration(5000L, 1000L)
                .addObserver(new CentralMonitor())
                .build();
        intersections.add(intersection);
    }

    public void start() {
        System.out.println("--- Starting Traffic Control System ---");
        executor = Executors.newFixedThreadPool(intersections.size());
        intersections.forEach(executor::submit);
    }

    public void stop() {
        System.out.println("--- Shutdown TrafficSystem Initiated ---");
        intersections.forEach(Intersection::stop);
        executor.shutdown();
        try {
            if(!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e){
            executor.shutdownNow();
        }
        System.out.println("--- Traffic System Shutdown Completed ---");
    }

}
