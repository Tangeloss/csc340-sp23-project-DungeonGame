package Dungeon;

public class Room {

    private int id; //place ID, stored when saving game, each room object has a different numerical id.
    private String description;
    private boolean playerHere;
    private boolean monsterHere;

    public Room(int id, String description, boolean playerHere, boolean monsterHere) {
        this.id = id;
        this.description = description;
        this.playerHere = playerHere;
        this.monsterHere = monsterHere;
    }

    public boolean isPlayerHere() {
        return playerHere;
    }

    public void setPlayerHere(boolean playerHere) {
        this.playerHere = playerHere;
    }

    public boolean isMonsterHere() {
        return monsterHere;
    }

    public void setMonsterHere(boolean monsterHere) {
        this.monsterHere = monsterHere;
    }

    public int getId() {
        return id;
    }

    public String getDescription(){return description;}

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", playerHere=" + playerHere +
                ", monsterHere=" + monsterHere +
                '}';
    }
}