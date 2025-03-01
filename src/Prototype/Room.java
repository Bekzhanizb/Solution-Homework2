package Prototype;

public class Room implements ClonableGameEntity {
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public void setName(String name) {
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