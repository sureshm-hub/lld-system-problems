package proj.hobby.lld.trafficController;

import java.util.concurrent.TimeUnit;

public class TrafficSystemDemo {

    public static void main(String[] args) {
        TrafficControlSystem trafficControlSystem = TrafficControlSystem.getInstance();
        trafficControlSystem.addIntersection(1, 3000L, 1000L);
        trafficControlSystem.addIntersection(2,7000l, 3000L);
        trafficControlSystem.addIntersection(3, 5000L, 2000L);

        trafficControlSystem.start();

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        trafficControlSystem.stop();
    }
}
