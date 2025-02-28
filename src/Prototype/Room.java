package Prototype;

public class Room implements ClonableGameEntity {
    private final String name;

    public Room(String name) {
        this.name = name;
    }

    @Override
    public ClonableGameEntity clone() {
        return new Room(this.name);
    }

    @Override
    public String toString() {
        return "Room: " + name;
    }
}