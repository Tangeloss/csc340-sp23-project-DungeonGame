package Dungeon;

public class Room {

    public int id;
    public boolean playerHere;
    public boolean monsterHere;

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
