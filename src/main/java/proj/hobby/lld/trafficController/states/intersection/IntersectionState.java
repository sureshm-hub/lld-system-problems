package proj.hobby.lld.trafficController.states.intersection;

import proj.hobby.lld.trafficController.Intersection;

public interface IntersectionState {
        void handle(Intersection context) throws InterruptedException;
}
