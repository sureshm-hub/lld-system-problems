package proj.hobby.lld.elevatorsystem.entities;


import proj.hobby.lld.elevatorsystem.enums.Direction;
import proj.hobby.lld.elevatorsystem.enums.RequestSource;

public class Request {
    private int floor;
    private Direction direction;
    private RequestSource source;

    public Request(int floor, Direction direction, RequestSource source) {
        this.floor = floor;
        this.direction = direction;
        this.source = source;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloor() {
        return floor;
    }

    public RequestSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Request["+getSource()+"] "+getFloor()+" direction "+getDirection();
    }
}