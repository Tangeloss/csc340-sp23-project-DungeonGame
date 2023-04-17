package Dungeon;

/**
 * Room class used to inform where the player is at any time, where the Monsters are, and what integer room
 * identifier should be used to store the game's state for saving.
 *
 * Currently, description is not implemented as a callable function. Could be expanded upon to improve immersion.
 */
public class Room {

    private int id; //place ID, stored when saving game, each room object has a different numerical id.
    private String description;
    private boolean playerHere;
    private boolean monsterHere;

    /**
     * @param id passed to set the integer numeral of the room, useful for tracking player position and information
     *           for saving the game.
     * @param description Not currently implemented, could be used to give some life and immersion to the game's rooms.
     * @param playerHere boolean set to true if player is, or has been, in the room. False if not there or hasn't been
     *                   there in the current game's runtime.
     * @param monsterHere boolean set to true for if a Monster was populated there by the Dungeon class' createDungeon
     *                    method.
     */
    public Room(int id, String description, boolean playerHere, boolean monsterHere) {
        this.id = id;
        this.description = description;
        this.playerHere = playerHere;
        this.monsterHere = monsterHere;
    }

    /**
     * @return True if player is there or has been in that room. False if has never been there or isn't there.
     */
    public boolean isPlayerHere() {
        return playerHere;
    }

    /**
     * @param playerHere Sets if a player has been in the room or is currently there.
     */
    public void setPlayerHere(boolean playerHere) {
        this.playerHere = playerHere;
    }

    /**
     * @return True if a monster is in the room. False if a monster is not in the room.
     */
    public boolean isMonsterHere() {
        return monsterHere;
    }

    /**
     * @param monsterHere Sets if a Monster is in the room.
     */
    public void setMonsterHere(boolean monsterHere) {
        this.monsterHere = monsterHere;
    }

    /**
     * @return Gets the integer ID of the room and returns.
     */
    public int getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    /*
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", playerHere=" + playerHere +
                ", monsterHere=" + monsterHere +
                '}';
    }

     */
}
