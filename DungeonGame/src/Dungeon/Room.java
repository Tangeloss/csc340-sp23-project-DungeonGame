package Dungeon;

public class Room {

    private int id; //place ID, stored when saving game, each room object has a different numerical id.
    private String description;
    private boolean playerHere;
    private boolean monsterHere;

    /*
    left, center and right rooms store ids of the next room player can traverse to.
    The only time center is valid is when the room only has one exit, otherwise it will present a left or a right.
     */
    private int left;
    private int center;
    private int right;

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
}
